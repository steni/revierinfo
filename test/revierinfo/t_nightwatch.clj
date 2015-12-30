(ns revierinfo.t-nightwatch
  (:use midje.sweet)
  (:require [revierinfo.nightwatch :as nw]
            [clj-time.core :as t])
  (:import [revierinfo.nightwatch Nightwatch]))

(facts "about legal dates"
       (fact "dates between April 15th and October 15th of any year are allowd"
             (nw/legal-date? (t/date-time 2010 04 16)) => true
             (nw/legal-date? (t/date-time 2019 04 15)) => true
             (nw/legal-date? (t/date-time 2010 04 14)) => false
             (nw/legal-date? (t/date-time 2015 04 16)) => true
             (nw/legal-date? (t/date-time 2020 10 15)) => true
             (nw/legal-date? (t/date-time 2010 10 16)) => false))

(facts "about 'nightwatch' creation"
       (fact "a nightwatch record can be created for any date between April 15 and October 15 (inclusive) of any year"
             (nw/create (t/date-time 2010 04 15)) => (contains {:date (t/date-time 2010 04 15) :members-on-duty nil})
             (nw/create (t/date-time 2010 10 15)) => (contains {:date (t/date-time 2010 10 15) :members-on-duty nil})
       (fact "a nigtwatch record cannot be created for dates before April 15 or after October 15"
             (nw/create (t/date-time 2010 04 14)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             (nw/create (t/date-time 2020 10 16)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             (nw/create (t/date-time 2009 12 01)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             )))

(facts "about assigning members for nightwatch duty"
       (fact "a member can be assigned for nightwatch duty"
             (nw/create (t/date-time 2010 04 15) CREATE MEMBER HERE ) => (contains {:date (t/date-time 2010 04 15) :members-on-duty nil})))
