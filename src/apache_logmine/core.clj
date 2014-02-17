(ns apache-logmine.core
  (:use [hiccup.core]
        [apache-logmine.mail]
        [apache-logmine.load]))



(def file "/modsec/apache/logs/latest.log")

(def data (group-by :ip (records-from-access-log file)))

(defn make-number[n]
  (try (Integer. n)
    (catch Exception e 0)))

(defn get-bytes[ip-data]
  (let [bytes-total (->> ip-data
                   (map #(make-number (% :bytes)))
                   (reduce +))
        ip ((first ip-data) :ip)]
    {bytes-total ip}))

(defn normalize-num[n]
  (/ (Float/parseFloat n) (* 1024 1024 1024)))

(defn top-ten[data]
  (->> data
       keys
       vec
       distinct
       (map #(get-bytes (data %)))
       (apply conj)
       sort
       reverse
       (take 10)
       (map reverse)))

(defn alert-style [n]
  (if (> (:percentage-used n) 90)
    "font-style: italic; font-weight:bolder; color: #B40404;"
    "color: white;"))

(defn format-html [report]
  ( html
    [:h2 {:style "color: green; text-align: left;"}
     (str  "Apache Log Analysis Report for: [" (java.util.Date.) "]")]
    [:hr]
    [:table {:style "border: 1px solid grey; width: 50%; background-color: #6E6E6E;"}
     [:tr {:style "color: black;"}
      [:th {:style " text-align: left;"} "Name"]
      [:th {:style " text-align: right;"} "Used GB"]
      [:th {:style " text-align: right;"} "Total GB"]
      [:th {:style " text-align: right;"} "% Used"]]
     (for [r (rest report)]
       [:tr  {:style (str  "" (alert-style r))}
        [:td {:style " text-align: left;"} (:cluster-name r)]
        [:td {:style " text-align: right;"} (Math/floor  (:used r))]
        [:td {:style " text-align: right;"} (Math/floor  (:total r))]
        [:td {:style " text-align: right;"} (Math/floor  (:percentage-used r))]])]))

(def report (html
             [:h1 "Hello"]))

(defn -main []
  (send-mail report))



(-main)


