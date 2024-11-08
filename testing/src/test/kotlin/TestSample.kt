import org.junit.jupiter.api.Test

class TestSample {

    @Test
    fun actionTest(){
        //friends	gifts	result
        //["muzi", "ryan", "frodo", "neo"]	["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]
        val friend = arrayOf("muzi", "ryan", "frodo", "neo")
        val gifts = arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi")
        Solution().solution(friend, gifts)

    }
    class Solution {
        // public int solution(String[] friends, String[] gifts) {
        fun solution(friends: Array<String>, gifts: Array<String>): Int {
            // 이차원 배열
            var giftCount = Array(friends.size) { IntArray(friends.size) }
            var giftIndex = Array(friends.size) { IntArray(friends.size) }

            // 주고받은 관계 입력
            for (i in friends.indices) {
                for (j in friends.indices) {
                    println("${friends[i]} ${friends[j]}")

                    gifts.find { it == "${friends[i]} ${friends[j]}" }
                        ?.let { giftCount[i][j]++ }
                }
            }

            return TODO();
        }
    }
}