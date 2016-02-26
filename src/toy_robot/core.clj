(ns toy-robot.core (:gen-class))
(require '[toy-robot.input-validation :as validate])
(require '[toy-robot.input-handling :as handle])
(require '[toy-robot.reporting :as reporting])
(require '[input.core :as input])

(defn input-loop []
  (loop [events []]
    (let [raw-input (read-line)
          input (input/sanitize raw-input)]
      (cond
        (validate/exit input)   nil
        (validate/place input)  (recur (conj events (handle/place input)))
        (validate/move input)   (recur (conj events (handle/move)))
        (validate/turn input)   (recur (conj events (handle/turn input)))
        (validate/report input) (do (println (reporting/pretty-report events))
                                  (recur events))
        :else                   (do (println "Move not recognized")
                                 (recur events)))
      )))

(defn -main []
  (println "welcome to the robot game!")
  (input-loop))
