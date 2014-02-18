(ns apache-logmine.dns)


(import 'javax.naming.directory.InitialDirContext)
(import 'java.util.Hashtable)

(def env (Hashtable.))
(.put env "java.naming.factory.initial" "com.sun.jndi.dns.DnsContextFactory")

(defn get-reverse-dns[ip]
  (let [reverse-ip (clojure.string/join "." (reverse (re-seq #"\d{1,3}" ip)))
        ctx (InitialDirContext. env)
        attr (.getAttributes ctx (str reverse-ip ".in-addr.arpa") (.split (String. "PTR,") ","))
        ret (.nextElement (.getAll (.next (.getAll attr))))]
    ret))

(defn do-dns-lookup[ip]
      (try
          (get-reverse-dns ip)
          (catch Exception e)))


