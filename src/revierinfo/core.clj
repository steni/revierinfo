(ns revierinfo.core)

(defn -main
  [& args]
  (println "Hello, World!"))

(defn first-element [sequence default]
  (if (nil? sequence)
    default
    (first sequence)))
