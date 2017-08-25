## Welcome to GitHub Pages

이 샘플 프로젝트는 Jedis를 통해 Redis와 연동하여 사용자 데이터를 캐시하고, SpringSession + Lettuce를 사용하여 세션 데이터를 공유하는 코드입니다.
SpringSession은 Jedis는 사용할 수 없고 Lettuce를 사용해야 합니다. 이것은 Lettuce가 Thread-safe하게 구현되어 있어 Redis와의 connection 관리가 쉬워지는 장점이 있기 때문입니다.
