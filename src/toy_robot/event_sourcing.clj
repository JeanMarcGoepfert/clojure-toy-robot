(ns toy-robot.event-sourcing)
(require '[clojure.core.match :refer [match]])
(require '[toy-robot.config :as config])
(require '[directions.core :as directions])

(defn- place [event-value]
  (assoc event-value :dir (directions/cardinal-to-int (event-value :dir))))

(defn- turn-left [old-dir]
  (if (> old-dir 0) (- old-dir 1) 3))

(defn- turn-right [old-dir]
  (if (< old-dir 3) (+ old-dir 1) 0))

(defn- turn [old-pos event-value]
  (let [old-dir (old-pos :dir)]
    (match [event-value]
           [:left]  (assoc old-pos :dir (turn-left old-dir))
           [:right] (assoc old-pos :dir (turn-right old-dir))
           )))

(defn- move [{:keys [x y dir] :as old-pos}]
  (match [dir]
         [0] (assoc old-pos :y (+ y 1))
         [1] (assoc old-pos :x (+ x 1))
         [2] (assoc old-pos :y (- y 1))
         [3] (assoc old-pos :x (- x 1))
         ))

(defn- match-event [acc event]
  (match [(event :type)]
         [:place] (place (event :value))
         [:turn]  (turn acc (event :value))
         [:move]  (move acc)))

(defn- is-in-range [n a b]
  (if (and (>= n a) (<= n b)) true false))

(defn- is-valid [{:keys [x y dir]}]
  (and (is-in-range x 0 config/board-size)
       (is-in-range y 0 config/board-size)
       (is-in-range dir 0 3)))

(defn source-event [acc event]
  (let [new-pos (match-event acc event)]
    (if (is-valid new-pos) new-pos acc)))
