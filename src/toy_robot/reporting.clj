(ns toy-robot.reporting)
(require '[toy-robot.event-sourcing :as es])
(require '[directions.core :as directions])

(defn report [events]
  (let [default {:x 0 :y 0 :dir 0}]
    (reduce es/source-event default events)))

(defn pretty-report [events]
  (let [{:keys [x y dir]} (report events)
        cardinal-dir (name (directions/int-to-cardinal dir))]
    (str "Currently at position " x ", " y " facing " cardinal-dir)))
