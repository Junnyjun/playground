#!/bin/bash

topic.sh \
 --create \
 --bootstrap-server localhost:9092 \
  --replication-factor 1 \ # 복제본의 수를 지정
  --partitions 1 \ # 파티션의 수를 지정
  --config retention.ms=1000 \ # 메시지 보관 시간을 지정
  --topic test

consumer.sh \
 --bootstrap-server localhost:9092 \ # kafka 서버 지정
   --topic test \ # 읽어올 topic 지정
  --group test-group \ # consumer group 지정
 # --property print.key=true \ # key 출력 여부
  # --property key.separator=, \ # key와 value 사이의 구분자
  --from-beginning # 처음부터 읽어오기
