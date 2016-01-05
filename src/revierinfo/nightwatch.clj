(ns revierinfo.nightwatch
  (:require [clj-time.core :as t]))

(defonce april 04)
(defonce october 10)
(defonce spring-start-day 15)
(defonce autumn-last-day 15)
(defonce max-members-for-nightwatch 2)

(defprotocol Attendable
  (add-attendee [this attendee]))

(defrecord Nightwatch [date members-on-duty]
  Attendable
  (add-attendee [this attendee]
    (if (< (count (:members-on-duty this)) max-members-for-nightwatch)
      (update-in this
                 [:members-on-duty] conj attendee)
      (throw (Exception. "Only two members can attend a nightwatch"))
      )))

(defn legal-date? [date]
  (let [y (t/year date)
        start (t/date-time y april spring-start-day)
        end (t/date-time y october (+ 1 autumn-last-day)) ]
    (t/within? (t/interval start end) date)))

(defn create [date & [members-on-duty]]
  (if (legal-date? date)
    (Nightwatch. date (conj [] members-on-duty))
    (throw (Exception. "Date must be between April 15 and October 15, inclusive"))))
