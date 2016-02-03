(ns revierinfo.t-member
  (:use midje.sweet)
  (:require [revierinfo.models.member :as member]))

(def steni (member/create "2013001" "Sten Morten" "Misund-Asphaug" ["93261911"]))

(facts "about `member`"
       (fact "it can divulge membership number"
             (:id steni) => "2013001" )
       (fact "there can only be one member with an id"
             (member/create "2013001" "Tonje" "Misund-Asphaug" ["92642141"])
             => throws Exception "Member with that id already exists") ;; TODO:
       ;; Should fail now before it is implemented
       )
