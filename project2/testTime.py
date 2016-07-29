import threading, time, httplib, random
#
urls = [
    "/project2/searchServlet?subMatch=true&title=spi",
    "/project2/searchServlet?subMatch=true&year=2005",
    "/project2/searchServlet?subMatch=true&director=j",
]

SERVER_NAME = "127.0.0.1:8080"
TEST_COUNT = 1000
#Create a thread class
class RequestThread(threading.Thread):
    # constructor
    def __init__(self, thread_name):
        threading.Thread.__init__(self)
        self.test_count = 0

    # run thread
    def run(self):
        
        i = 0
        while i < TEST_COUNT:
            self.test_performace()
            i += 1
        #self.test_other_things()

    def test_performace(self):
        conn = httplib.HTTPConnection(SERVER_NAME)

        for i in range(0, random.randint(0, 100)):
            #
            url = urls[random.randint(0, len(urls) - 1)];

            print url
            try:
                conn.request("GET", url)
                rsps = conn.getresponse()
                if rsps.status == 200:
                    # read the response data 
                    data = rsps.read()
                self.test_count += 1
            
            except:
                continue
            
        conn.close()
        
# main start the function

# start time
start_time = time.time()
threads = []
# total thread
thread_count = 100 

i = 0
while i < thread_count:
    t = RequestThread("thread" + str(i))
    threads.append(t)
    t.start()
    i += 1
# 
word = ""
while True:
    word = raw_input("cmd:")
    if word == "s":
        time_span = time.time() - start_time
        all_count = 0
        for t in threads:
            all_count += t.test_count
        print "%s Request/Second" % str(all_count / time_span)
    elif word == "e":

        TEST_COUNT = 0
        for t in threads:
            t.join(0)
        break 
    
       