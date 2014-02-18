(ns apache-logmine.html
  (:use [hiccup.core]
            [apache-logmine.utils]
            [apache-logmine.dns]))


(defn alert-style [n]
  (if (> (:percentage-used n) 90)
    "font-style: italic; font-weight:bolder; color: #B40404;"
    "color: white;"))

(defn format-report[dat total-bytes]
  ( html
    [:h2 {:style "color: green; text-align: left;"}
     (str  "Apache Log Analysis Report for: [" (java.util.Date.) "]")]
    [:hr]
    [:table {:style "border: 1px solid grey; width: 50%; background-color: #6E6E6E;"}
     [:tr {:style "color: black; border: 1px solid white;"}
      [:th {:style " text-align: left;"} "DNS"]
      [:th {:style " text-align: left;"} "IP"]
      [:th {:style " text-align: right;"} "MB"]
      [:th {:style " text-align: right;"} "%"]]
     (for [r (rest dat)]
       (let [[ip size] r
               perc (int (* (/ (float size) (float total-bytes)) 100))]
         [:tr  {:style "border: 1px solid grey; width: 50%; background-color: #6A6A6A;"}
          [:td {:style " text-align: left;"} (do-dns-lookup ip)]
          [:td {:style " text-align: left;"} ip]
          [:td {:style " text-align: right;"} (normalize-num size)]
          [:td {:style " text-align: right;"} perc]]))]))


 (* (/ 80 100) 100)

