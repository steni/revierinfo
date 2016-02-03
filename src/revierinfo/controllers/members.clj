(ns revierinfo.controllers.members
  (:require [revierinfo.models.member :as member]))

(defn get [id]
  (member/get-by-id id))

(defn get-all []
  (member/get-all))
