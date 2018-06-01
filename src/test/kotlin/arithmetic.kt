import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec
import kotlin.math.pow
import kotlin.system.measureTimeMillis

class Arithmetic : StringSpec({
    "P31 (*) Determine whether a given integer number is prime." {
        (0..13).filter { it.isPrime() } shouldBe listOf(2, 3, 5, 7, 11, 13)
    }

    "P32 (*) Determine the greatest common divisor of two positive integer numbers." {
        gcd(1, 2) shouldBe 1
        gcd(4, 2) shouldBe 2
        gcd(5, 13) shouldBe 1
        gcd(36, 63) shouldBe 9
    }

    "P33 (*) Determine whether two positive integer numbers are coprime." {
        35.isCoprimeTo(64) shouldBe true
    }

    "P34 (*) Calculate Euler's totient function phi(m)." {
        10.totient() shouldBe 4
        10090.totient() shouldBe 4032
    }

    "P35 (*) Determine prime factors of a given positive integer." {
        315.primeFactors() shouldBe listOf(3, 3, 5, 7)
    }

    "P36 (*) Determine the prime factors of a given positive integer (2)." {
        315.primeFactorMultiplicity() shouldBe listOf(Pair(3, 2), Pair(5, 1), Pair(7, 1))
    }

    "P37 (*) Calculate Euler's totient function phi(m) (improved)." {
        10.totientImproved() shouldBe 4
        10090.totientImproved() shouldBe 4032
    }

    "P38 (*) Compare the two methods of calculating Euler's totient function." {
        val a = measureTimeMillis { (1..1000).forEach { it.totient() } }
        val b = measureTimeMillis { (1..1000).forEach { it.totientImproved() } }
        a shouldNotBe b
    }

    "P39 (*) A list of prime numbers." {
        listPrimesInRange(7..31) shouldBe listOf(7, 11, 13, 17, 19, 23, 29, 31)
    }

    "P40 (*) Goldbach's conjecture." {
        4.goldbach() shouldBe Pair(2, 2)
        28.goldbach() shouldBe Pair(5, 23)
    }

    "P41 (*) A list of Goldbach compositions." {
        printGoldbachList(9..20) shouldBe listOf(
                Triple(10, 3, 7),
                Triple(12, 5, 7),
                Triple(14, 3, 11),
                Triple(16, 3, 13),
                Triple(18, 5, 13),
                Triple(20, 3, 17))

        printGoldbachListLimited(2..3000, 50) shouldBe listOf(
                Triple(992, 73, 919),
                Triple(1382, 61, 1321),
                Triple(1856, 67, 1789),
                Triple(1928, 61, 1867),
                Triple(2078, 61, 2017),
                Triple(2438, 61, 2377),
                Triple(2512, 53, 2459),
                Triple(2530, 53, 2477),
                Triple(2618, 61, 2557),
                Triple(2642, 103, 2539))
    }
})


fun Int.isPrime(): Boolean =
        this > 1 && !(2..this / 2).any {
            this % it == 0
        }

fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

fun Int.isCoprimeTo(that: Int): Boolean = gcd(this, that) == 1

fun Int.totient(): Int = (1..this).count { this.isCoprimeTo(it) }

fun Int.primeFactors(): List<Int> {
    if (this.isPrime()) return listOf(this)

    val primeFactor = (2..(this / 2))
            .filter { it.isPrime() }
            .find { this % it == 0 }

    return if (primeFactor == null) emptyList()
    else listOf(primeFactor) + (this / primeFactor).primeFactors()
}

fun Int.primeFactorMultiplicity(): List<Pair<Int, Int>> = this.primeFactors()
        .groupBy { it }
        .map { Pair(it.key, it.value.size) }

fun Int.totientImproved(): Int {
    val list = this.primeFactorMultiplicity().map { (p, m) -> (p - 1) * p.toDouble().pow(m - 1).toInt() }
    return if (list.isEmpty()) 0 else list.reduce { acc, i -> acc * i }
}

fun listPrimesInRange(range: IntRange): List<Int> = range.filter { it.isPrime() }

fun Int.goldbach(): Pair<Int, Int> {
    return when (this) {
        2 -> Pair(1, 1)
        3 -> Pair(1, 2)
        4 -> Pair(2, 2)
        else -> {
            val primes = listPrimesInRange(2..this)
            val first = primes.find { x -> primes.any { y -> x + y == this } }
            return Pair(first!!, this - first)
        }
    }
}

fun printGoldbachList(range: IntRange): List<Triple<Int, Int, Int>> =
        range.filter { it % 2 == 0 }
                .map {
                    val pair = it.goldbach()
                    Triple(it, pair.first, pair.second)
                }

fun printGoldbachListLimited(range: IntRange, limit: Int): List<Triple<Int, Int, Int>> {
    val printGoldbachList = printGoldbachList(range)
    return printGoldbachList.filter { it.second >= limit && it.third >= limit }
}