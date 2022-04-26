package  com.cba.transactionaccount.util

object RegexPatterns {
    const val BECS_TRANSFER_MAX_DESCRIPTION_LENGTH = 18
    const val REGEX_BECS_CHARS_MAX_PATTERN_INVERTED = "[^(?!.*A-Za-z \\+@!\\^\\$%&'\\(\\)\\*\\-:;=\\?\\.#_,\\[\\]/\\d]*"
    val REGEX_NON_DIGITS = """\D+""".toRegex()
    val REGEX_ONE_OR_MORE_NON_DIGIT = """\D+""".toRegex()
    val REGEX_LEADING_ZEROS = """^0+(?!$)""".toRegex()

    val REGEX_DECIMAL_AMOUNT_PATTERN = """^\d+|\d{1,3},\d{3}|\d{1,3},\d{3}\.\d{0,2}$|\d+\.\d{0,2}$""".toRegex()
    val REGEX_DECIMAL_FORMATTED_AMOUNT_PATTERN = """^\$\d+|\$\d{1,3},\d{3}|\$\d{1,3},\d{3}\.\d{0,2}$|\$\d+\.\d{0,2}$""".toRegex()
    val REGEX_DECIMAL_BIG_AMOUNT_PATTERN = """^\d{1,3},\d{3},\d{3}\.\d{0,2}$|^\d{1,3},\d{3},\d{3}$""".toRegex()
    val REGEX_DECIMAL_BIG_AMOUNT_FORMATTED_PATTERN = """^\$\d{1,3},\d{3},\d{3}\.\d{0,2}$|^\$\d{1,3},\d{3},\d{3}$""".toRegex()
    val REGEX_BECS_CHARS_MAX_PATTERN = """[A-Za-z +@!^$%&'()*\-:;=?.#_,\[\]/\d]*""".toRegex()
    val REGEX_BECS_CHARS_WITH_NEW_LINE_MAX_PATTERN = """[A-Za-z\s+@!^$%&'()*\-:;=?.#_,\[\]/\d]*""".toRegex()
    val REGEX_BPAY_REFERENCE_PATTERN = """^[0-9]{2,20}$""".toRegex()
    val REGEX_BPAY_BILLER_CODE_PATTERN = """\d{4,10}""".toRegex()
    val REGEX_ALL_ZEROES_PATTERN = """^0+$""".toRegex()
    val REGEX_ONE_OR_MORE_SPACES = """\s+""".toRegex()
}