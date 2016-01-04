
(ns revierinfo.t-member
  (:use midje.sweet)
  (:require [revierinfo.member :as member]))

(def steni (member/create "2013001" "Sten Morten" "Misund-Asphaug" ["93261911"]))

(facts "about `member`"
       (fact "it can divulge membership number"
             (:id steni) => "2013001" ))
