(ns revierinfo.t-nightwatch
  (:use midje.sweet)
  (:require
   [revierinfo.member :as member]
   [revierinfo.nightwatch :as nw]
   [clj-time.core :as t])

  (:import [revierinfo.nightwatch Nightwatch]))

(facts "about legal dates"
       (fact "dates between April 15th and October 15th of any year are allowed"
             (nw/legal-date? (t/date-time 2010 04 16)) => true
             (nw/legal-date? (t/date-time 2019 04 15)) => true
             (nw/legal-date? (t/date-time 2010 04 14)) => false
             (nw/legal-date? (t/date-time 2015 04 16)) => true
             (nw/legal-date? (t/date-time 2020 10 15)) => true
             (nw/legal-date? (t/date-time 2010 10 16)) => false))

(facts "about 'nightwatch' creation"
       (fact "a nightwatch record can be created for any date between April 15 and October 15 (inclusive) of any year"
             (nw/create (t/date-time 2010 04 15)) => (contains {:date (t/date-time 2010 04 15) :members-on-duty [nil]})
             (nw/create (t/date-time 2010 10 15)) => (contains {:date (t/date-time 2010 10 15) :members-on-duty [nil]})
       (fact "a nigtwatch record cannot be created for dates before April 15 or after October 15"
             (nw/create (t/date-time 2010 04 14)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             (nw/create (t/date-time 2020 10 16)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             (nw/create (t/date-time 2009 12 01)) => (throws Exception "Date must be between April 15 and October 15, inclusive")
             )))

(facts "about assigning members for nightwatch duty"
       (let [member (member/create "2013001" "Sten Morten" "Stensen" ["93261911"])
             member2 (member/create "2013002" "Tonje" "Tonjesen" ["92642141"])
             member3 (member/create "2013003" "Jasper" "Jaspersen" ["12345678"])
             time (t/date-time 2010 04 15)
             nightwatch (nw/create time member)
             nightwatch-with-two-members (nw/add-attendee nightwatch member2)
             ]
         (fact "a member can be assigned for nightwatch duty"
               nightwatch => {:date time :members-on-duty [member]})
         (fact "can add another member to the nightwatch "
               nightwatch-with-two-members => {:date time :members-on-duty [member member2]}
               )
         (fact "cannot add a thirds member to the nighwatch"
               (nw/add-attendee nightwatch-with-two-members member3) => (throws Exception "Only two members can attend a nightwatch")
               )))
