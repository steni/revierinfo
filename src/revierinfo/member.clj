(ns revierinfo.member)

(defrecord Member [id first-name last-name phonenumbers])

(defn create [id first-name last-name phonenumbers]
  (Member. id first-name last-name phonenumbers))
