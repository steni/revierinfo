(ns revierinfo.t-core
  (:use midje.sweet)               ;; <<==
  (:require [revierinfo.core :as core]))

(facts "about `first-element`"
  (fact "it normally returns the first element"
    (core/first-element [1 2 3] :default) => 1
    (core/first-element '(1 2 3) :default) => 1))
