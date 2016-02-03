(ns revierinfo.services.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            monger.json)
  (:import [com.mongodb MongoOptions ServerAddress]
           org.bson.types.ObjectId))

(defonce conn (mg/connect))
(defonce db   (mg/get-db conn "revier"))

(defn insert [collection data]
  (mc/insert-and-return db collection (merge {:_id (ObjectId.)} data)))

(defn get-all [collection]
  (mc/find-maps db collection))

(defn find [collection find-function]
  (mc/find-maps db collection find-function))

(defn find-one [collection find-function]
  (mc/find-one-as-map db collection find-function))

(defn count [collection data]
  (mc/count db collection data))


(comment (println (str "Hei=================" (mc/find-one db "documents" {:first_name "John"}))))
(comment (println (str "===============" (count "documents" {:first_name "Johns"}))))
