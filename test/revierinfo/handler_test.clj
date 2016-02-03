(ns revierinfo.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [revierinfo.handler :refer :all]
            [revierinfo.models.member :as member]
            [revierinfo.models.nightwatch :as nw]
            [clj-time.core :as t]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "getting a member"
    (let [member (member/create "2013001" "Sten Morten" "Stensen" ["93261911"])
          time (t/date-time 2010 04 15)
          nightwatch (nw/create time member)
          response (app (mock/request :get "/member/2013001"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "2013001"))
      (is (.contains (:body response) "Sten Morten"))
      (is (not (.contains (:body response) "Tonje")))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
