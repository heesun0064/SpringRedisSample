## Welcome to GitHub Pages

이 샘플 프로젝트는 Jedis를 통해 Redis와 연동하여 사용자 데이터를 캐시하고, SpringSession + Lettuce를 사용하여 세션 데이터를 공유하는 코드입니다.
SpringSession은 Jedis는 사용할 수 없고 Lettuce를 사용해야 합니다. 이것은 Lettuce가 Thread-safe하게 구현되어 있어 Redis와의 connection 관리가 쉬워지는 장점이 있기 때문입니다.
또한 Redis는 Sentinel을 지원하여 1개의 Master와 여러개의 Slave를 모니터링하다가 Master가 죽으면 Slave 중의 하나를 Master로 승격시키는 기능을 지원합니다. 본 샘플 프로젝트는 Sentinel 지원을 위한 XML 설정이 포함되어 있습니다.
