import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class Lists : StringSpec({
    "P01 (*) Find the last element of a list." {
        last(listOf(1, 1, 2, 3, 5, 8)) shouldBe 8
    }

    "P02 (*) Find the last but one element of a list."{
        penultimate(listOf(1, 1, 2, 3, 5, 8)) shouldBe 5
    }

    "P03 (*) Find the Nth element of a list." {
        nth(2, listOf(1, 1, 2, 3, 5, 8)) shouldBe 2
    }

    "P04 (*) Find the number of elements of a list." {
        length(listOf(1, 1, 2, 3, 5, 8)) shouldBe 6
    }

    "P05 (*) Reverse a list." {
        reverse(listOf(1, 1, 2, 3, 5, 8)) shouldBe listOf(8, 5, 3, 2, 1, 1)
    }

    "P06 (*) Find out whether a list is a palindrome." {
        isPalindrome(listOf(1, 2, 3, 2, 1)) shouldBe true
    }

    "P07 (*) Flatten a nested list structure." {
        flatten(listOf(listOf(1, 1), 2, listOf(3, listOf(5, 8)))) shouldBe listOf(1, 1, 2, 3, 5, 8)
    }

    "P08 (*) Eliminate consecutive duplicates of list elements." {
        compress("aaaabccaadeeee".toList()) shouldBe listOf('a', 'b', 'c', 'a', 'd', 'e')
    }
})

// helpers
fun <T> List<T>.tail(): List<T> = this.subList(1, this.size)


// solutions
fun <T> last(list: List<T>): T = list[list.lastIndex]

fun <T> penultimate(list: List<T>): T = list[list.lastIndex - 1]

fun <T> nth(i: Int, list: List<T>): T = if (i == 0) list[0] else nth(i - 1, list.tail())

fun <T> length(list: List<T>): Int = if (list.isEmpty()) 0 else 1 + length(list.tail())

fun <T> reverse(list: List<T>): List<T> = if (list.size <= 1) list else reverse(list.tail()) + list.first()

fun <T> isPalindrome(list: List<T>): Boolean = list == reverse(list)

fun flatten(list: List<Any>): List<Any> = flattenHelper(emptyList(), list)
fun flattenHelper(acc: List<Any>, list: List<Any>): List<Any> = when {
    list.isEmpty() -> acc
    list.first() is List<*> -> {
        val firstList = list.first() as List<Any>
        flattenHelper(acc + firstList.first(), firstList.tail() + list.tail())
    }
    else -> flattenHelper(acc + list.first(), list.tail())
}
/*
@Suppress("UNCHECKED_CAST")
fun flatten(list: List<Any>): List<Any> =
        list.flatMap {
            if (it is List<*>) flatten(it as List<Any>) else listOf(it)
        }
*/

fun <T> compress(list: List<T>): List<T> = compressHelper(emptyList(), list)
fun <T> compressHelper(acc: List<T>, list: List<T>): List<T> = when {
    list.isEmpty() -> acc
    acc.isEmpty() -> compressHelper(listOf(list.first()), list.tail())
    acc.last() == list.first() -> compressHelper(acc, list.tail())
    else -> compressHelper(acc + list.first(), list.tail())
}
/*
fun <T> compress(list: List<T>) =
        list.fold(emptyList<T>()) { result, value ->
            if (result.isNotEmpty() && result.last() == value) result
            else result + value
        }
*/