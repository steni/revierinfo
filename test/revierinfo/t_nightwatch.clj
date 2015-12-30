(ns revierinfo.t-nightwatch
  (:use midje.sweet)
  (:require [revierinfo.nightwatch :as nw]
            [clj-time.core :as t])
  (:import [revierinfo.nightwatch Nightwatch]))

(facts "about legal dates"
       (fact "dates between April 15th and October 15th of any year are allowd"
             (nw/legal-date? (t/date-time 2010 04 16)) => true
             (nw/legal-date? (t/date-time 2010 04 15)) => true
             (nw/legal-date? (t/date-time 2010 04 14)) => false
             (nw/legal-date? (t/date-time 2015 04 16)) => true
             (nw/legal-date? (t/date-time 2017 04 14)) => false
             (nw/legal-date? (t/date-time 2010 10 15)) => true
             (nw/legal-date? (t/date-time 2010 10 16)) => false))

(comment (facts "about 'nightwatch'"
                (fact "a nightwatch record can be created for any date between April 15 and October 15 (inclusive) of any year"
                      (nw/create (t/date-time 2010 04 15) []) => {:date (t/date-time 2010 04 15)}
                      (nw/create (t/date-time 2010 10 15) []) => {:date (t/date-time 2010 10 15)})
                (fact "a nigtwatch record cannot be created for dates before April 15 or after October 15"
                      (nw/create (t/date-time 2010 04 14) []) => (throws Exception "Date must be betwen April 15 and October 15, inclusive")
                      )
                ))
