(ns revierinfo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :refer :all]
            [revierinfo.controllers.members :as members]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/member/:id" [id]
       (generate-string (members/get id)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
