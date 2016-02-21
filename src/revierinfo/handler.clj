(ns revierinfo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :refer :all]
            [revierinfo.controllers.members :as members]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/member/:id" [id]
       (generate-string (members/get id)))
  (GET "/member/" []
       (generate-string (members/get-all)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main []
  (ring/run-jetty #'app {:port 8080 :join? false}))
