(ns apache-logmine.mail
  (:require [postal.core :as postal]))

(defn send-mail [report]
    (postal/send-message
     {:from "santools@iseintlogrpt01"
      :to "jennifer.morgan@ise.ie"
      :subject "DAILY ISE SAN Report"
      :body [{:type "text/html"
              :content report}]}))
