(ns apache-logmine.mail
  (:require [postal.core :as postal]))

(defn send-mail [report]
    (postal/send-message
     {:from "apache-logmine@iseintlogrpt01"
      :to "admin@ise.ie"
      :subject "DAILY Apache Log Report"
      :body [{:type "text/html"
              :content report}]}))
