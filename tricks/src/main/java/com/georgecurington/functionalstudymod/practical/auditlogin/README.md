## real world problem ##
#1- Audit logins to web service: Question description (Java 8)

# Problem Description As It was given to me:
From the call:  This question focuses on multi-threading and execution service.   Candidates can write their own separate thread or they can have Java provide the thread through an execution service. 
ABC Corp has a popular rest API that is used by millions of customers. 
ABC Corp has a change requirement to audit every login to this API.  
To facilitate this, a method writeAuditLog has been implemented. This method takes two parameters, a log message, and a user id. The log message should be of the format "Login success at <iso_date_timestamp>".
This audit feature has to be implemented without impacting the response time of the existing api. The writes to the audit database take between 1 and 500 milliseconds.
Your task is to implement the auditLogin method with minimal impact to the existing response time of the API.

Discussion:
From what I get from that fragile description, it seems that we have the following variables:
1. A generic log message indicating successful login
2. A user identification
3. An ISO timestamp. 

This sounds like a simple problem. I think the best approach would be to off load the actual
logging of the message to some parallely running process via some type of queing mechanism.
Once the successful login occurs, the method is passed the relevant information. That method
in turn will just dump the message into a queue and be done with it. Some other thread or even
multiple threads will read the queue and process the message.

So we will have the writeAuditLog method defined:

public static StatusLogAudit writeAuditLog( String logmessage, String userId );

Maybe define an enum describing the result of the method invocation:
enum StausLogAudit {
successQueueWrite, unsuccesQueueWrite }

The item to be queue should be a class also:
interface AuditQueue {
public String getUserId();
public String getLogmessage();
public Date getLogtime();
}

and the actual class object:
class LoginAudit implements AuditQueue { ....... }

Generally, this method should never fail and if it fails backend processes will handle
any types of errors that might occur.

The real work wold occur via a thread that will continually poll the queue looking for 
work to do. One such work item would be to log a message to the database.

A simple simulation should demonstrate this approach using a simple java BlockingQueue.
In this case the Blocking Queue would be of unlimited size so as to never Block during 
queue insertion. This could be extended to multiple queues so that the contention of too many requests hitting the queue at the same time could be held to a minimum. On the back end you would also have many threads reading the queue looking for work items to process. 

In the real world, the queue would most likely be a real queue such as RabbitMQ , ActiveMG or some other type of distributed queing system
