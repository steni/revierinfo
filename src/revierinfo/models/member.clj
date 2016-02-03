(ns revierinfo.models.member
  (:require [revierinfo.services.db :as db]))

(defrecord Member [id first-name last-name phonenumbers])

(defn store [member] (db/insert "members" member))

(defn get-by-id [id] (db/find-one "members" {:id id}))

(defn get-all [] (db/find "members" {}))

(defn create [id first-name last-name phonenumbers]
  ;; TODO: check that id doesn't already exist
  (store (Member. id first-name last-name phonenumbers)))
