(ns toy-robot.input-validation)

(defn place [input]
  (boolean (re-matches #"place \d+ \d+ (north|east|south|west)" input)))

(defn move [input] (= input "move"))

(defn turn [input] (boolean (re-matches #"turn (left|right)" input)))

(defn report [input] (= input "report"))

(defn exit [input] (= input "exit"))
