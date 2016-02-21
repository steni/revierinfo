(ns revierinfo.core)



(defn first-element [sequence default]
  (if (nil? sequence)
    default
    (first sequence)))
