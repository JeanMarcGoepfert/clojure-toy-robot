(ns input.core)
(require '[clojure.string :as string])

(defn sanitize [input]
  (string/replace (-> input .toLowerCase .trim) #"\s+" " "))
