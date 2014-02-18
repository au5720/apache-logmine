(ns apache-logmine.geo
  (:require [cheshire.core :as json]))

(.put (System/getProperties) "http.proxyHost" "localhost")
(.put (System/getProperties) "http.proxyPort" "3128")

(defn get-country[ip]
  ((json/parse-string
    (slurp
     (str "http://freegeoip.net/json/" ip))) "country_name"))
