(ns toy-robot.input-handling)
(require '[clojure.string :as string])

(defn place [input]
  (let [parts (string/split input #" ")
        x (Integer. (parts 1))
        y (Integer. (parts 2))
        dir (keyword (parts 3))]
    {:type :place :value {:x x :y y :dir dir}}))

(defn move [] {:type :move :value 1})

(defn turn [input]
  (let [parts (string/split input #" ")
        dir (keyword (parts 1))]
    {:type :turn :value dir }))
