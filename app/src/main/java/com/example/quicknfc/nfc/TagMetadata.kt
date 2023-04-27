package com.example.quicknfc.nfc

data class TagMetadata(
    var techAvailable: Array<String>,
    var isWritable: Boolean?,
    var canMakeReadOnly: Boolean?,
    var ndefType: String?,
    var memorySize: Int?,
    var sak: String?,
    var atqa: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagMetadata

        if (!techAvailable.contentEquals(other.techAvailable)) return false
        if (isWritable != other.isWritable) return false
        if (canMakeReadOnly != other.canMakeReadOnly) return false
        if (ndefType != other.ndefType) return false
        if (memorySize != other.memorySize) return false
        if (sak != other.sak) return false
        if (atqa != other.atqa) return false

        return true
    }

    override fun hashCode(): Int {
        var result = techAvailable.contentHashCode()
        result = 31 * result + (isWritable?.hashCode() ?: 0)
        result = 31 * result + (canMakeReadOnly?.hashCode() ?: 0)
        result = 31 * result + (ndefType?.hashCode() ?: 0)
        result = 31 * result + (memorySize ?: 0)
        result = 31 * result + (sak?.hashCode() ?: 0)
        result = 31 * result + (atqa?.hashCode() ?: 0)
        return result
    }
}
