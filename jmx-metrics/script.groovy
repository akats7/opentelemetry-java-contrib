import org.slf4j.LoggerFactory

//  - "kafka.controller:type=KafkaController,name=ActiveControllerCount"
//  - "kafka.controller:type=KafkaController,name=OfflinePartitionsCount"
//  - "kafka.controller:type=ControllerStats,name=UncleanLeaderElectionsPerSec"
//  - "kafka.controller:type=ControllerStats,name=LeaderElectionRateAndTimeMs"

def logger = LoggerFactory.getLogger("script")

//def kafkaController = otel.mbeans(["kafka.controller:type=KafkaController,name=ActiveControllerCount",
//                                   "kafka.controller:type=KafkaController,name=OfflinePartitionsCount",
//                                   "kafka.controller:type=ControllerStats,name=UncleanLeaderElectionsPerSec",
//                                   "kafka.controller:type=ControllerStats,name=LeaderElectionRateAndTimeMs"])
//otel.instrument(kafkaController, "kafka_controller_value", "controller is active on broker",
//  "{controllers}",
//  [
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "Value", otel.&longValueCallback)
//otel.instrument(kafkaController, "kafka_controller_count", "controller is active on broker",
//  "{controllers}",
//  [
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "Count", otel.&longCounterCallback)

//- "java.lang:type=Memory,*"
//- "java.lang:type=GarbageCollector,*"
//- "java.lang:type=OperatingSystem,*"

//def memory = otel.mbean("java.lang:type=Memory")
//
//otel.instrument(memory,"java_lang_memory_heapmemoryusage", "heap usage",
//  "by",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "HeapMemoryUsage", otel.&longValueCallback)

//need to fix naming for garbage collection

def garbageCollectorYoung = otel.mbean("java.lang:type=GarbageCollector,name=G1 Young Generation,*", ["Valid": {attribute -> attribute == true ? 1 : 0}])
otel.instrument(garbageCollectorYoung,
  "java_lang_g1_young_generation_collectioncount",
  "total number of collections that have occurred",
  "1",
  [
    "name" : { mbean -> mbean.name().getKeyProperty("name") },
    "type" : { mbean -> mbean.name().getKeyProperty("type") }
  ],
  "Valid", otel.&longCounterCallback)

//otel.instrument(garbageCollectorYoung,
//  "java_lang_g1_young_generation_collectiontime",
//  "the approximate accumulated collection elapsed time in milliseconds", "ms",
//  [
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "CollectionTime", otel.&longCounterCallback)
//
//def garbageCollectorOld = otel.mbean("java.lang:type=GarbageCollector,name=G1 Old Generation,*")
//otel.instrument(garbageCollectorOld,
//  "java_lang_g1_old_generation_collectioncount",
//  "total number of collections that have occurred",
//  "1",
//  [
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "CollectionCount", otel.&longCounterCallback)
//
//otel.instrument(garbageCollectorOld,
//  "java_lang_g1_old_generation_collectiontime",
//  "the approximate accumulated collection elapsed time in milliseconds", "ms",
//  [
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "CollectionTime", otel.&longCounterCallback)
//
//def operatingSystem = otel.mbean("java.lang:type=OperatingSystem")
//otel.instrument(operatingSystem, "java_lang_open_file_descriptor_count", "current open file descriptor count",
//  "Count",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "OpenFileDescriptorCount", otel.&longValueCallback)
//otel.instrument(operatingSystem, "java_lang_max_file_descriptor_count", "max file descriptor count",
//  "Count",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "MaxFileDescriptorCount", otel.&longValueCallback)

//  - "kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec,topic=*"
//  - "kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=*"
//  - "kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec,topic=*"
//  - "kafka.server:type=BrokerTopicMetrics,name=TotalProduceRequestsPerSec,topic=*"
//  - "kafka.server:type=BrokerTopicMetrics,name=TotalFetchRequestsPerSec,topic=*"
//  - "kafka.server:type=BrokerTopicMetrics,name=BytesRejectedPerSec,*"
//  - "kafka.server:type=BrokerTopicMetrics,name=FailedFetchRequestsPerSec,*"
//  - "kafka.server:type=KafkaRequestHandlerPool,name=RequestHandlerAvgIdlePercent,*"
//  - "kafka.server:type=DelayedOperationPurgatory,name=PurgatorySize,delayedOperation=Fetch,*"
//  - "kafka.server:type=DelayedOperationPurgatory,name=PurgatorySize,delayedOperation=Produce,*"

//def kafkaServerV_C_OMR = otel.mbeans(["kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec,topic=*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec,topic=*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=TotalProduceRequestsPerSec,topic=*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=TotalFetchRequestsPerSec,topic=*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=BytesRejectedPerSec,*",
//                                      "kafka.server:type=BrokerTopicMetrics,name=FailedFetchRequestsPerSec,*",
//                                      "kafka.server:type=KafkaRequestHandlerPool,name=RequestHandlerAvgIdlePercent,*",
//                                      "kafka.server:type=DelayedOperationPurgatory,name=PurgatorySize,delayedOperation=Fetch,*",
//                                      "kafka.server:type=DelayedOperationPurgatory,name=PurgatorySize,delayedOperation=Produce,*"])
//otel.instrument(kafkaServerV_C_OMR,
//  "kafka_server_count",
//  "kafka server count attributes",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") },
//    "delayedOperation" : { mbean -> mbean.name().getKeyProperty("delayedOperation") }
//  ],
//  "Count", otel.&longCounterCallback)
//otel.instrument(kafkaServerV_C_OMR,
//  "kafka_server_value",
//  "kafka server count attributes",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") },
//    "delayedOperation" : { mbean -> mbean.name().getKeyProperty("delayedOperation") }
//  ],
//  "Value", otel.&longValueCallback)
//otel.instrument(kafkaServerV_C_OMR,
//  "kafka_server_oneminuterate",
//  "kafka server one minute rate attributes",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") },
//    "delayedOperation" : { mbean -> mbean.name().getKeyProperty("delayedOperation") }
//  ],
//  "OneMinuteRate", otel.&doubleValueCallback)
//
////  - "kafka.server:type=ReplicaManager,name=UnderMinIsrPartitionCount,*"
////  - "kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions,*"
////  - "kafka.server:type=ReplicaManager,name=PartitionCount,*"
////  - "kafka.server:type=ReplicaManager,name=LeaderCount,*"
////  - "kafka.server:type=ReplicaFetcherManager,name=MaxLag,clientId=Replica,*"
//
//def kafkaServer_V = otel.mbeans(["kafka.server:type=ReplicaManager,name=UnderMinIsrPartitionCount,*",
//                                 "kafka.server:type=ReplicaManager,name=UnderReplicatedPartitions,*",
//                                 "kafka.server:type=ReplicaManager,name=PartitionCount,*",
//                                 "kafka.server:type=ReplicaManager,name=LeaderCount,*",
//                                 "kafka.server:type=ReplicaFetcherManager,name=MaxLag,clientId=Replica,*"])
//
//otel.instrument(kafkaServer_V,
//  "kafka_server_value",
//  "kafka server value attributes",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "clientId" : { mbean -> mbean.name().getKeyProperty("clientId") }
//  ],
//  "Value", otel.&longValueCallback)
//
////   - "kafka.server:type=ReplicaManager,name=IsrExpandsPerSec,*"
////  - "kafka.server:type=ReplicaManager,name=IsrShrinksPerSec,*"
//
//def kafkaServer_C = otel.mbeans(["kafka.server:type=ReplicaManager,name=IsrExpandsPerSec,*",
//                                 "kafka.server:type=ReplicaManager,name=IsrShrinksPerSec,*",
//                                 "kafka.server:type=SessionExpireListener,name=ZooKeeperSyncConnectsPerSec,*",
//                                 "kafka.server:type=SessionExpireListener,name=ZooKeeperAuthFailuresPerSec,*",
//                                 "kafka.server:type=SessionExpireListener,name=ZooKeeperReadOnlyConnectsPerSec,*",
//                                 "kafka.server:type=SessionExpireListener,name=ZooKeeperExpiresPerSec,*"])
//otel.instrument(kafkaServer_C,
//  "kafka_server_count",
//  "kafka server count attributes",
//  "{Gauge}",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") }
//  ],
//  "Count", otel.&longCounterCallback)
//
//def kafkaServerSocket = otel.mbean("kafka.server:type=socket-server-metrics,listener=SSL,networkProcessor=*")
//
//otel.instrument(kafkaServerSocket,
//  "kafka_server_connection_count",
//  "kafka server connection count",
//  "{connections}",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "listener" : { mbean -> mbean.name().getKeyProperty("listener") },
//    "networkProcessor" : { mbean -> mbean.name().getKeyProperty("networkProcessor") }
//  ],
//  "connection-count", otel.&longCounterCallback)
//
//otel.instrument(kafkaServerSocket,
//  "kafka_server_connection_creation_rate",
//  "kafka server connection creation rate",
//  "{connection rate}",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "listener" : { mbean -> mbean.name().getKeyProperty("listener") },
//    "networkProcessor" : { mbean -> mbean.name().getKeyProperty("networkProcessor") }
//  ],
//  "connection-creation-rate", otel.&doubleValueCallback)
//
//// TODO change units to not ms when type is not request metrics
//
//def kafkaNetwork = otel.mbeans(["kafka.network:type=RequestMetrics,name=RequestsPerSec,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchConsumer,*",
//                                "kafka.network:type=RequestMetrics,name=RequestsPerSec,request=FetchFollower,*",
//                                "kafka.network:type=RequestMetrics,name=RequestQueueTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=LocalTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=RemoteTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=ResponseQueueTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=ResponseSendTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=Produce,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=FetchConsumer,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=FetchFollower,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=UpdateMetadata,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=Metadata,*",
//                                "kafka.network:type=RequestMetrics,name=TotalTimeMs,request=ListOffsets,*",
//                                "kafka.network:type=SocketServer,name=NetworkProcessorAvgIdlePercent",
//                                "kafka.network:type=RequestChannel,name=RequestQueueSize"])
//
//otel.instrument(kafkaNetwork,
//  "kafka_network_count",
//  "kafka network count",
//  "ms",
//  [
//    "request" : { mbean -> mbean.name().getKeyProperty("request") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "Count", otel.&longCounterCallback)
//otel.instrument(kafkaNetwork,
//  "kafka_network_value",
//  "kafka network value",
//  "ms",
//  [
//    "request" : { mbean -> mbean.name().getKeyProperty("request") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "Value", otel.&doubleValueCallback)
//otel.instrument(kafkaNetwork,
//  "kafka_network_99thPercentile",
//  "kafka network 99th percentile",
//  "ms",
//  [
//    "request" : { mbean -> mbean.name().getKeyProperty("request") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "99thPercentile", otel.&doubleValueCallback)
//otel.instrument(kafkaNetwork,
//  "kafka_network_mean",
//  "kafka network mean",
//  "ms",
//  [
//    "request" : { mbean -> mbean.name().getKeyProperty("request") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "type" : { mbean -> mbean.name().getKeyProperty("type") }
//  ],
//  "Mean", otel.&doubleValueCallback)
//
//def kafkaLog = otel.mbeans(["kafka.log:type=LogFlushStats,name=LogFlushRateAndTimeMs" ,
//                            "kafka.log:type=Log,name=LogEndOffset,partition=*,topic=*",
//                            "kafka.log:type=Log,name=LogStartOffset,partition=*,topic=*"])
//
//otel.instrument(kafkaLog,
//  "kafka_log_count",
//  "kafka log count",
//  "{logs}",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "partition" : { mbean -> mbean.name().getKeyProperty("partition") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "Count", otel.&longCounterCallback)
//
//otel.instrument(kafkaLog,
//  "kafka_log_value",
//  "kafka log value",
//  "{logs}",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "partition" : { mbean -> mbean.name().getKeyProperty("partition") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "Value", otel.&longValueCallback)
//
//def kafkaCluster = otel.mbeans(["kafka.cluster:type=Partition,name=UnderReplicated,partition=*,topic=*",
//                                "kafka.cluster:type=Partition,name=ReplicasCount,partition=*,topic=*",
//                                "kafka.cluster:type=Partition,name=InSyncReplicasCount,partition=*,topic=*"])
//
//otel.instrument(kafkaCluster,
//  "kafka_cluster_value",
//  "kafka cluster value",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "name" : { mbean -> mbean.name().getKeyProperty("name") },
//    "partition" : { mbean -> mbean.name().getKeyProperty("partition") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "Value", otel.&longValueCallback)
//
//def kafkaConsumer = otel.mbeans(["kafka.consumer:type=consumer-fetch-manager-metrics,client-id=*,topic=*",
//                                 "kafka.consumer:type=consumer-fetch-manager-metrics,client-id=*"])
//
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_value",
//  "kafka consumer value",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "Value", otel.&longValueCallback)
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_fetch_size_avg",
//  "kafka consumer fetch size average",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "fetch-size-avg", otel.&doubleValueCallback)
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_bytes_consumed_rate",
//  "kafka consumer bytes consumed rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "bytes-consumed-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_records_per_request_avg",
//  "kafka consumer records per request avg",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "records-per-request-avg", otel.&doubleValueCallback)
//
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_records_consumed_rate",
//  "kafka consumer records consumed rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "records-consumed-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_records_lag_max",
//  "kafka consumer records lag max",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "records-lag-max", otel.&longValueCallback)
//otel.instrument(kafkaConsumer,
//  "kafka_consumer_fetch_rate",
//  "kafka consumer fetch rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//  ],
//  "fetch-rate", otel.&doubleValueCallback)
//
//
//def kafkaProducer = otel.mbean("kafka.producer:type=producer-metrics,client-id=*")
//
//otel.instrument(kafkaProducer,
//  "kafka_producer_response_rate",
//  "kafka producer response rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "response-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_request_rate",
//  "kafka producer request rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "request-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_connection_count",
//  "kafka producer connection count",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "connection-count", otel.&longValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_request_latency_avg",
//  "kafka producer request latency avg",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "request-latency-avg", otel.&doubleValueCallback)
//
//otel.instrument(kafkaProducer,
//  "kafka_producer_outgoing_byte_rate",
//  "kafka producer outgoing byte rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "outgoing-byte-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_io_wait_time_ns_avg",
//  "kafka producer io wait time",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "io-wait-time-ns-avg", otel.&longValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_batch_size_avg",
//  "kafka producer batch size avg",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "batch-size-avg", otel.&longValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_record_error_rate",
//  "kafka producer record error rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "record-error-rate", otel.&doubleValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_record_size_avg",
//  "kafka producer record size average",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "record-size-avg", otel.&longValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_records_per_request_avg",
//  "kafka producer records per request",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "records-per-request-avg", otel.&longValueCallback)
//otel.instrument(kafkaProducer,
//  "kafka_producer_record_send_rate",
//  "kafka producer record send rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") }
//  ],
//  "record-send-rate", otel.&longValueCallback)
//
//def kafkaProducerTopic = otel.mbean("kafka.producer:type=producer-topic-metrics,client-id=*,topic=*")
//
//otel.instrument(kafkaProducerTopic,
//  "kafka_producer_topic_record_send_rate",
//  "kafka producer topic record send rate",
//  "Gauge",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//    "topic" : { mbean -> mbean.name().getKeyProperty("topic") }
//
//  ],
//  "record-send-rate", otel.&longValueCallback)
//
//def kafkaConnect1 = otel.mbean("kafka.connect:type=connect-worker-metrics")
//
//def kafka1Arr = ["connector-count","task-count"]
//
//for (el in kafka1Arr){
//  otel.instrument(kafkaConnect1,
//    "kafka_connect_worker_metrics_" + el,
//    "kafka connect worker metrics",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    ],
//    el,
//    otel.&longValueCallback)
//}
//
//
//def kafkaConnect2 = otel.mbean("kafka.connect:type=connect-worker-metrics,connector=*")
//
//def kafka2Arr = ["connector-total-task-count","connector-running-task-count","connector-failed-task-count","connector-destroyed-task-count"]
//
//for (el in kafka2Arr){
//  otel.instrument(kafkaConnect2,
//    "kafka_connect_worker_" + el,
//    "kafka connect worker metrics",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "connector" : { mbean -> mbean.name().getKeyProperty("connector") }
//    ],
//    el,
//    otel.&longValueCallback)
//}
//
//def kafkaConnect4 = otel.mbean("kafka.connect:type=connect-node-metrics,client-id=*,node-id=*")
//
//def kafka4Arr = ["request-rate","response-rate","request-latency-avg","request-size-avg"]
//
//for (el in kafka4Arr){
//  otel.instrument(kafkaConnect4,
//    "kafka_connect_node_" + el,
//    "kafka connect node",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "client-id" : { mbean -> mbean.name().getKeyProperty("client-id") },
//      "node-id" : { mbean -> mbean.name().getKeyProperty("node-id") }
//    ],
//    el,
//    otel.&longValueCallback)
//}
//
//def kafkaConnect5 = otel.mbean("kafka.connect:type=connector-task-metrics,connector=*,task=*")
//
//def kafka5Arr = ["offset-commit-failure-percentage","batch-size-avg","offset-commit-avg-time-ms"]
//
//for (el in kafka5Arr){
//  otel.instrument(kafkaConnect5,
//    "kafka_connect_connector_task_" + el,
//    "kafka connect connector task",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "connector" : { mbean -> mbean.name().getKeyProperty("connector") },
//      "task" : { mbean -> mbean.name().getKeyProperty("task") }
//    ],
//    el,
//    otel.&longValueCallback)
//}
//
//def kafkaConnect6 = otel.mbean("kafka.connect:type=connector-task-metrics,connector=*,task=*")
//
//
//def kafkaConnect7 = otel.mbean("kafka.connect:type=source-task-metrics,connector=*,task=*")
//
//def kafka7Arr = ["source-record-write-rate","source-record-poll-rate","source-record-active-count-avg","poll-batch-avg-time-ms"]
//
//for (el in kafka7Arr){
//  otel.instrument(kafkaConnect7,
//    "kafka_connect_source_task_" + el,
//    "kafka connect source task",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "connector" : { mbean -> mbean.name().getKeyProperty("connector") },
//      "task" : { mbean -> mbean.name().getKeyProperty("task") }
//    ],
//    el,
//    otel.&doubleValueCallback)
//}
//
//def kafkaConnect8 = otel.mbean("kafka.connect:type=sink-task-metrics,connector=*,task=*")
//
//def kafka8Arr = ["sink-record-write-rate","sink-record-send-rate","sink-record-active-count-avg","offset-commit-completion-rate","put-batch-max-time-ms"]
//
//for (el in kafka8Arr){
//  otel.instrument(kafkaConnect8,
//    "kafka_connect_sink_task_" + el,
//    "kafka connect sink task",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "connector" : { mbean -> mbean.name().getKeyProperty("connector") },
//      "task" : { mbean -> mbean.name().getKeyProperty("task") }
//    ],
//    el,
//    otel.&doubleValueCallback)
//}
//
//otel.instrument(kafkaConnect8,
//  "kafka_connect_sink_task_partition-count",
//  "kafka connect sink task",
//  "Guage",
//  [
//    "type" : { mbean -> mbean.name().getKeyProperty("type") },
//    "connector" : { mbean -> mbean.name().getKeyProperty("connector") },
//    "task" : { mbean -> mbean.name().getKeyProperty("task") }
//  ],
//  "partition-count",
//  otel.&longValueCallback)
//
//def kafkaConnect9 = otel.mbean("kafka.connect:type=connect-metrics,client-id=*")
//
//def kafka9Arr = ["request-rate","request-size-avg","connection-count","io-wait-ratio","network-io-rate","response-rate","outgoing-byte-rate"]
//
//for (el in kafka9Arr) {
//  otel.instrument(kafkaConnect9,
//    "kafka_connect_metrics_" + el,
//    "kafka connect metrics",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "client-id": { mbean -> mbean.name().getKeyProperty("client-id") }
//    ],
//    el,
//    otel.&doubleValueCallback)
//}
//
//def kafkaConnect = otel.mbean("kafka.connect:type=task-error-metrics,connector=*,task=*")
//
//def kafkaArr = ["total-record-errors","total-records-skipped","last-error-timestamp"]
//
//for (el in kafkaArr){
//  otel.instrument(kafkaConnect,
//    "kafka_connect_task_error_" + el,
//    "kafka connect task error",
//    "Guage",
//    [
//      "type" : { mbean -> mbean.name().getKeyProperty("type") },
//      "connector" : { mbean -> mbean.name().getKeyProperty("connector") },
//      "task" : { mbean -> mbean.name().getKeyProperty("task") }
//    ],
//    el,
//    otel.&longValueCallback)
//}
//
//def addUnderscores(String input) {
//
//  def output = input.replaceAll(" ","_")
//
//  return output
//}
