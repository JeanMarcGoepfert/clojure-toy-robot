(ns directions.core)

(def valid-directions [:north :east :south :west])

(defn cardinal-to-int [dir]
  (.indexOf valid-directions dir))

(defn int-to-cardinal [int-dir]
  (valid-directions int-dir))
