(ns apache-logmine.load)

(defn- extract-records-from-line [line-from-access-log]
  (let [[ _ ip _ _ datetime request response bytes referer agent]
        (re-find #"^([\d.]+) (\S+) (\S+) \[([\w:/]+\s[+\-]\d{4})\] \"(.+?)\" (\d{3}) (\d+) \"([^\"]+)\" \"([^\"]+)\"" line-from-access-log)]
    {:ip ip
     :datetime datetime
     :request request
     :response response
     :bytes bytes
     :referer referer
     :agent agent}))

(defn- as-dataseries
  [access-log-lines]
  (map extract-records-from-line access-log-lines))


(defn- records-from-access-log
  [filename]
  (as-dataseries
   (line-seq
    (clojure.java.io/reader filename))))

(defn load-data[file]
  (group-by :ip (records-from-access-log file)))












