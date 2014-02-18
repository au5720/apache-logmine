(ns apache-logmine.core
  (:use  [hiccup.core]
             [apache-logmine.mail]
             [apache-logmine.load]
             [apache-logmine.html]
             [apache-logmine.utils] ))


(defn -main []
  (let [file  "/tmp/apache-latest.log"
         data (load-data file)
         total-bytes (total-bytes data)
         t-ten (top-ten data)
         report (format-report t-ten total-bytes)]
   (send-mail report)))




