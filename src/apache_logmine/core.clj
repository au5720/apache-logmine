(ns apache-logmine.core)

(defn extract-records-from-line [line-from-access-log]
  (let [[ _ ip _ _ datetime request response bytes referer agent]
        (re-find #"^([\d.]+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] \"(.+?)\" (\d{3}) (\d+) \"([^\"]+)\" \"([^\"]+)\"" line-from-access-log)]
    #_[ip datetime request response bytes referer agent]
    {:ip ip
     :datetime datetime
     :request request
     :response response
     :bytes bytes
     :referer referer
     :agent agent}))

(defn as-dataseries
  [access-log-lines]
  (map extract-records-from-line access-log-lines))


(defn records-from-access-log
  [filename]
  (as-dataseries
   (line-seq
    (clojure.java.io/reader "/modsec/apache/logs/latest.log"))))

(def file "/modsec/apache/logs/latest.log")

(def data (group-by :ip (records-from-access-log file)))

(defn make-number[n]
  (try (Integer. n)
    (catch Exception e 0)))

(defn bytes[ip-data]
  (let [bytes-total (->> ip-data
                   (map #(make-number (% :bytes)))
                   (reduce +))
        ip ((first ip-data) :ip)]
    {bytes-total ip}))

(defn top-ten[data]
  (->> data
       keys
       vec
       distinct
       (map #(bytes (data %)))
       (apply conj)
       sort
       reverse
       (take 10)))


(->> (top-ten data)
    (map #(reverse %)))














