(ns apache-logmine.core
  (:use  [hiccup.core]
             [apache-logmine.mail]
             [apache-logmine.load]
             [apache-logmine.html]
             [apache-logmine.utils] ))


(defn -main []
  (let [file  "/modsec/apache/logs/latest.log"
         data (load-data file)
         total-bytes (total-bytes data)
         t-ten (top-ten data)
         report (format-report t-ten total-bytes)]
   (send-mail report)))

(-main)


(with-precision 1 1.2567987897987)
