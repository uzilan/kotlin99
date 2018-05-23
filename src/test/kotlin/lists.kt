import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec
import java.util.*

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

    "P09 (*) Pack consecutive duplicates of list elements into sublists." {
        pack("aaaabccaadeeee".toList()) shouldBe listOf(listOf('a', 'a', 'a', 'a'), listOf('b'), listOf('c', 'c'), listOf('a', 'a'), listOf('d'), listOf('e', 'e', 'e', 'e'))
    }

    "P10 (*) Run-length encoding of a list." {
        encode("aaaabccaadeeee".toList()) shouldBe listOf(Pair(4, 'a'), Pair(1, 'b'), Pair(2, 'c'), Pair(2, 'a'), Pair(1, 'd'), Pair(4, 'e'))
    }

    "P11 (*) Modified run-length encoding." {
        encodeModified("aaaabccaadeeee".toList()) shouldBe listOf(Pair(4, 'a'), 'b', Pair(2, 'c'), Pair(2, 'a'), 'd', Pair(4, 'e'))
    }

    "P12 (*) Decode a run-length encoded list." {
        decode(listOf(Pair(4, 'a'), Pair(1, 'b'), Pair(2, 'c'), Pair(2, 'a'), Pair(1, 'd'), Pair(4, 'e'))) shouldBe listOf('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    }

    "P13 (*) Run-length encoding of a list (direct solution)." {
        encodeDirect("aaaabccaadeeee".toList()) shouldBe listOf(Pair(4, 'a'), Pair(1, 'b'), Pair(2, 'c'), Pair(2, 'a'), Pair(1, 'd'), Pair(4, 'e'))
    }

    "P14 (*) Duplicate the elements of a list." {
        duplicate("abccd".toList()) shouldBe listOf('a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'd', 'd')
    }


    "P15 (*) Duplicate the elements of a list a given number of times."{
        duplicateN(3, "abccd".toList()) shouldBe (listOf('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd'))
    }

    "P16 (*) Drop every Nth element from a list." {
        drop(3, "abcdefghijk".toList()) shouldBe listOf('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')
    }

    "P17 (*) Split a list into two parts." {
        split(3, "abcdefghijk".toList()) shouldBe Pair(listOf('a', 'b', 'c'), listOf('d', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))
    }

    "P18 (*) Extract a slice from a list." {
        slice(3, 7, "abcdefghijk".toList()) shouldBe listOf('d', 'e', 'f', 'g')
    }

    "P19 (*) Rotate a list N places to the left." {
        rotate(3, "abcdefghijk".toList()) shouldBe listOf('d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'a', 'b', 'c')
        rotate(-2, "abcdefghijk".toList()) shouldBe listOf('j', 'k', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    }

    "P20 (*) Remove the Kth element from a list." {
        removeAt(1, "abcd".toList()) shouldBe Pair(listOf('a', 'c', 'd'), 'b')
    }

    "P21 (*) Insert an element at a given position into a list." {
        insertAt('X', 1, "abcd".toList()) shouldBe listOf('a', 'X', 'b', 'c', 'd')
    }

    "P22 (*) Create a list containing all integers within a given range." {
        range(4, 9) shouldBe listOf(4, 5, 6, 7, 8, 9)
    }

    "P23 (*) Extract a given number of randomly selected elements from a list." {
        randomSelect(3, "abcdefgh".toList(), Random(321)) shouldBe (listOf('e', 'c', 'f'))
    }

    "P24 (*) Lotto: Draw N different random numbers from the set 1..M." {
        lotto(3, 49, Random(213)) shouldBe listOf(31, 23, 6)
    }

    "P25 (*) Generate a random permutation of the elements of a list." {
        randomPermute("abcdef".toList(), Random(123)) shouldBe listOf('d', 'b', 'e', 'f', 'a', 'c')
    }

    "P26 (**) Generate the combinations of K distinct objects chosen from the N elements of a list." {
        combinations(3, "abcde".toList()) shouldBe listOf(listOf('c', 'b', 'a'), listOf('d', 'b', 'a'), listOf('e', 'b', 'a'), listOf('d', 'c', 'a'), listOf('e', 'c', 'a'), listOf('e', 'd', 'a'), listOf('d', 'c', 'b'), listOf('e', 'c', 'b'), listOf('e', 'd', 'b'), listOf('e', 'd', 'c'))
    }

//    "P27 (**) Group the elements of a set into disjoint subsets." {
//        val groups = group3(listOf("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
//        // groups.forEach { println(it) }
//        groups should containsAll(listOf(listOf("Aldo", "Beat"), listOf("Carla", "David", "Evi"), listOf("Flip", "Gary", "Hugo", "Ida")))
//        groups.size shouldBe(1260)
//    }

    "P28 (*) Sorting a list of lists according to length of sublists." {
        lengthSort(listOf("abc".toList(), "de".toList(), "fgh".toList(), "de".toList(), "ijkl".toList(), "mn".toList(), "o".toList())) shouldBe listOf(listOf('o'), listOf('d', 'e'), listOf('d', 'e'), listOf('m', 'n'), listOf('a', 'b', 'c'), listOf('f', 'g', 'h'), listOf('i', 'j', 'k', 'l'))
        lengthFreqSort(listOf("abc".toList(), "de".toList(), "fgh".toList(), "de".toList(), "ijkl".toList(), "mn".toList(), "o".toList())) shouldBe listOf(listOf('i', 'j', 'k', 'l'), listOf('o'), listOf('a', 'b', 'c'), listOf('f', 'g', 'h'), listOf('d', 'e'), listOf('d', 'e'), listOf('m', 'n'))
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
@Suppress("UNCHECKED_CAST")
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

fun <T> pack(list: List<T>): List<List<T>> =
        if (list.isEmpty()) emptyList()
        else {
            val packed = list.takeWhile { it == list.first() }
            listOf(packed) + pack(list.drop(packed.size))
        }

fun <T> encode(list: List<T>): List<Pair<Int, T>> = pack(list).map { Pair(it.size, it.first()) }

fun <T> encodeModified(list: List<T>): List<Any> = pack(list).map {
    when {
        it.size == 1 -> it.first()
        else -> Pair(it.size, it.first())
    }!!
}

fun <T> decode(list: List<Pair<Int, T>>): List<T> = list.flatMap { (count, value) -> List(count, { value }) }

fun <T> encodeDirect(list: List<T>): List<Pair<Int, T>> =
        list.fold(emptyList()) { result, value ->
            if (result.isEmpty()) listOf(Pair(1, value))
            else {
                val last = result.last()
                if (last.second == value) result.dropLast(1) + Pair(last.first + 1, value)
                else result + Pair(1, value)
            }
        }

fun <T> duplicate(list: List<T>): List<T> = list.flatMap { listOf(it, it) }

fun <T> duplicateN(count: Int, list: List<T>): List<T> = list.flatMap { element -> List(count, { element }) }

fun <T> drop(count: Int, list: List<T>): List<T> = list.filterIndexed { index, _ -> (index + 1) % count != 0 }

fun <T> split(count: Int, list: List<T>): Pair<List<T>, List<T>> = Pair(list.take(count), list.drop(count))

fun <T> slice(from: Int, to: Int, list: List<T>): List<T> = list.subList(from, to)

fun <T> rotate(count: Int, list: List<T>): List<T> {
    val realCount = if (count >= 0) count else list.size + count
    return list.drop(realCount) + list.take(realCount)
}

fun <T> removeAt(index: Int, list: List<T>): Pair<List<T>, T> = Pair(list.filterIndexed { i, _ -> i != index }, list[index])

fun <T> insertAt(element: T, index: Int, list: List<T>): List<T> = list.take(index) + element + list.drop(index)

fun range(from: Int, to: Int): List<Int> = (from..to).toList()

fun <T> randomSelect(count: Int, list: List<T>, random: Random = Random()): List<T> =
        if (count == 0) emptyList()
        else {
            val value = list[random.nextInt(list.size)]
            randomSelect(count - 1, list.filter { it != value }, random) + value
        }

fun lotto(count: Int, max: Int, random: Random = Random()): List<Int> = (0 until count).map { random.nextInt(max) }

fun <T> randomPermute(list: List<T>, random: Random): List<T> = randomSelect(list.size, list, random)

fun <T> combinations(count: Int, list: List<T>): List<List<T>> =
        if (count == 0) listOf(emptyList())
        else list.flatMapTails { subList ->
            combinations(count - 1, subList.tail()).map { (it + subList.first()) }
        }

private fun <T> List<T>.flatMapTails(f: (List<T>) -> (List<List<T>>)): List<List<T>> =
        if (isEmpty()) emptyList()
        else f(this) + this.tail().flatMapTails(f)

fun <T> group3(list: List<T>): List<List<List<T>>> =
        combinations(2, list).flatMap { listOfTwo ->
            val filteredList = list.filterNot { listOfTwo.contains(it) }
            combinations(3, filteredList).flatMap { listOfThree ->
                val filteredList2 = filteredList.filterNot { listOfThree.contains(it) }
                combinations(4, filteredList2).map { listOf(it, listOfThree, listOfTwo) }
            }
        }

fun <T> group(sizes: List<Int>, list: List<T>): List<List<List<T>>> =
        if (sizes.isEmpty()) listOf(emptyList())
        else combinations(sizes.first(), list).flatMap { combination ->
            val filteredList = list.filterNot { combination.contains(it) }
            group(sizes.tail(), filteredList).map { it + listOf(combination) }
        }

fun <T> lengthSort(list: List<List<T>>): List<List<T>> = list.sortedBy { it.size }
fun <T> lengthFreqSort(list: List<List<T>>): List<List<T>> {
    val sizesAndFrequencies = list.groupBy { it.size }.mapValues { it.value.size }
    return list.sortedBy { sizesAndFrequencies[it.size] }
}