/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'DozerWgpsSvMsg'
 * message type.
 */

package ch.ethz.permasense.tinyos2x.messages;

public class DozerWgpsSvMsg extends ch.ethz.permasense.tinyos2x.messages.DataHeaderMsg {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 30;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 190;

    /** Create a new DozerWgpsSvMsg of size 30. */
    public DozerWgpsSvMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new DozerWgpsSvMsg of the given data_length. */
    public DozerWgpsSvMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg with the given data_length
     * and base offset.
     */
    public DozerWgpsSvMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg using the given byte array
     * as backing store.
     */
    public DozerWgpsSvMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public DozerWgpsSvMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public DozerWgpsSvMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg embedded in the given message
     * at the given base offset.
     */
    public DozerWgpsSvMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new DozerWgpsSvMsg embedded in the given message
     * at the given base offset and length.
     */
    public DozerWgpsSvMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <DozerWgpsSvMsg> \n";
      try {
        s += "  [header.seqNr=0x"+Long.toHexString(get_header_seqNr())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.originatorID=0x"+Long.toHexString(get_header_originatorID())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.aTime.low=0x"+Long.toHexString(get_header_aTime_low())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [header.aTime.high=0x"+Long.toHexString(get_header_aTime_high())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.carrierphase=0x"+Long.toHexString(get_payload_carrierphase())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.pseudorange=0x"+Long.toHexString(get_payload_pseudorange())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.doppler=0x"+Long.toHexString(get_payload_doppler())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.sv=0x"+Long.toHexString(get_payload_sv())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.signalstrength=0x"+Long.toHexString(get_payload_signalstrength())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [payload.lliandmqi=0x"+Long.toHexString(get_payload_lliandmqi())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.seqNr
    //   Field type: int
    //   Offset (bits): 0
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.seqNr' is signed (false).
     */
    public static boolean isSigned_header_seqNr() {
        return false;
    }

    /**
     * Return whether the field 'header.seqNr' is an array (false).
     */
    public static boolean isArray_header_seqNr() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.seqNr'
     */
    public static int offset_header_seqNr() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.seqNr'
     */
    public static int offsetBits_header_seqNr() {
        return 0;
    }

    /**
     * Return the value (as a int) of the field 'header.seqNr'
     */
    public int get_header_seqNr() {
        return (int)getUIntBEElement(offsetBits_header_seqNr(), 16);
    }

    /**
     * Set the value of the field 'header.seqNr'
     */
    public void set_header_seqNr(int value) {
        setUIntBEElement(offsetBits_header_seqNr(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.seqNr'
     */
    public static int size_header_seqNr() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.seqNr'
     */
    public static int sizeBits_header_seqNr() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.originatorID
    //   Field type: int
    //   Offset (bits): 16
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.originatorID' is signed (false).
     */
    public static boolean isSigned_header_originatorID() {
        return false;
    }

    /**
     * Return whether the field 'header.originatorID' is an array (false).
     */
    public static boolean isArray_header_originatorID() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.originatorID'
     */
    public static int offset_header_originatorID() {
        return (16 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.originatorID'
     */
    public static int offsetBits_header_originatorID() {
        return 16;
    }

    /**
     * Return the value (as a int) of the field 'header.originatorID'
     */
    public int get_header_originatorID() {
        return (int)getUIntBEElement(offsetBits_header_originatorID(), 16);
    }

    /**
     * Set the value of the field 'header.originatorID'
     */
    public void set_header_originatorID(int value) {
        setUIntBEElement(offsetBits_header_originatorID(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.originatorID'
     */
    public static int size_header_originatorID() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.originatorID'
     */
    public static int sizeBits_header_originatorID() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.aTime.low
    //   Field type: int
    //   Offset (bits): 32
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.aTime.low' is signed (false).
     */
    public static boolean isSigned_header_aTime_low() {
        return false;
    }

    /**
     * Return whether the field 'header.aTime.low' is an array (false).
     */
    public static boolean isArray_header_aTime_low() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.aTime.low'
     */
    public static int offset_header_aTime_low() {
        return (32 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.aTime.low'
     */
    public static int offsetBits_header_aTime_low() {
        return 32;
    }

    /**
     * Return the value (as a int) of the field 'header.aTime.low'
     */
    public int get_header_aTime_low() {
        return (int)getUIntBEElement(offsetBits_header_aTime_low(), 16);
    }

    /**
     * Set the value of the field 'header.aTime.low'
     */
    public void set_header_aTime_low(int value) {
        setUIntBEElement(offsetBits_header_aTime_low(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.aTime.low'
     */
    public static int size_header_aTime_low() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.aTime.low'
     */
    public static int sizeBits_header_aTime_low() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: header.aTime.high
    //   Field type: short
    //   Offset (bits): 48
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'header.aTime.high' is signed (false).
     */
    public static boolean isSigned_header_aTime_high() {
        return false;
    }

    /**
     * Return whether the field 'header.aTime.high' is an array (false).
     */
    public static boolean isArray_header_aTime_high() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'header.aTime.high'
     */
    public static int offset_header_aTime_high() {
        return (48 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'header.aTime.high'
     */
    public static int offsetBits_header_aTime_high() {
        return 48;
    }

    /**
     * Return the value (as a short) of the field 'header.aTime.high'
     */
    public short get_header_aTime_high() {
        return (short)getUIntBEElement(offsetBits_header_aTime_high(), 8);
    }

    /**
     * Set the value of the field 'header.aTime.high'
     */
    public void set_header_aTime_high(short value) {
        setUIntBEElement(offsetBits_header_aTime_high(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'header.aTime.high'
     */
    public static int size_header_aTime_high() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'header.aTime.high'
     */
    public static int sizeBits_header_aTime_high() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.carrierphase
    //   Field type: long
    //   Offset (bits): 56
    //   Size (bits): 64
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.carrierphase' is signed (false).
     */
    public static boolean isSigned_payload_carrierphase() {
        return false;
    }

    /**
     * Return whether the field 'payload.carrierphase' is an array (false).
     */
    public static boolean isArray_payload_carrierphase() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.carrierphase'
     */
    public static int offset_payload_carrierphase() {
        return (56 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.carrierphase'
     */
    public static int offsetBits_payload_carrierphase() {
        return 56;
    }

    /**
     * Return the value (as a long) of the field 'payload.carrierphase'
     */
    public long get_payload_carrierphase() {
        return (long)getUIntBEElement(offsetBits_payload_carrierphase(), 64);
    }

    /**
     * Set the value of the field 'payload.carrierphase'
     */
    public void set_payload_carrierphase(long value) {
        setUIntBEElement(offsetBits_payload_carrierphase(), 64, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.carrierphase'
     */
    public static int size_payload_carrierphase() {
        return (64 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.carrierphase'
     */
    public static int sizeBits_payload_carrierphase() {
        return 64;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.pseudorange
    //   Field type: long
    //   Offset (bits): 120
    //   Size (bits): 64
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.pseudorange' is signed (false).
     */
    public static boolean isSigned_payload_pseudorange() {
        return false;
    }

    /**
     * Return whether the field 'payload.pseudorange' is an array (false).
     */
    public static boolean isArray_payload_pseudorange() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.pseudorange'
     */
    public static int offset_payload_pseudorange() {
        return (120 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.pseudorange'
     */
    public static int offsetBits_payload_pseudorange() {
        return 120;
    }

    /**
     * Return the value (as a long) of the field 'payload.pseudorange'
     */
    public long get_payload_pseudorange() {
        return (long)getUIntBEElement(offsetBits_payload_pseudorange(), 64);
    }

    /**
     * Set the value of the field 'payload.pseudorange'
     */
    public void set_payload_pseudorange(long value) {
        setUIntBEElement(offsetBits_payload_pseudorange(), 64, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.pseudorange'
     */
    public static int size_payload_pseudorange() {
        return (64 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.pseudorange'
     */
    public static int sizeBits_payload_pseudorange() {
        return 64;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.doppler
    //   Field type: long
    //   Offset (bits): 184
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.doppler' is signed (false).
     */
    public static boolean isSigned_payload_doppler() {
        return false;
    }

    /**
     * Return whether the field 'payload.doppler' is an array (false).
     */
    public static boolean isArray_payload_doppler() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.doppler'
     */
    public static int offset_payload_doppler() {
        return (184 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.doppler'
     */
    public static int offsetBits_payload_doppler() {
        return 184;
    }

    /**
     * Return the value (as a long) of the field 'payload.doppler'
     */
    public long get_payload_doppler() {
        return (long)getUIntBEElement(offsetBits_payload_doppler(), 32);
    }

    /**
     * Set the value of the field 'payload.doppler'
     */
    public void set_payload_doppler(long value) {
        setUIntBEElement(offsetBits_payload_doppler(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.doppler'
     */
    public static int size_payload_doppler() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.doppler'
     */
    public static int sizeBits_payload_doppler() {
        return 32;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.sv
    //   Field type: short
    //   Offset (bits): 216
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.sv' is signed (false).
     */
    public static boolean isSigned_payload_sv() {
        return false;
    }

    /**
     * Return whether the field 'payload.sv' is an array (false).
     */
    public static boolean isArray_payload_sv() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.sv'
     */
    public static int offset_payload_sv() {
        return (216 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.sv'
     */
    public static int offsetBits_payload_sv() {
        return 216;
    }

    /**
     * Return the value (as a short) of the field 'payload.sv'
     */
    public short get_payload_sv() {
        return (short)getUIntBEElement(offsetBits_payload_sv(), 8);
    }

    /**
     * Set the value of the field 'payload.sv'
     */
    public void set_payload_sv(short value) {
        setUIntBEElement(offsetBits_payload_sv(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.sv'
     */
    public static int size_payload_sv() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.sv'
     */
    public static int sizeBits_payload_sv() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.signalstrength
    //   Field type: short
    //   Offset (bits): 224
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.signalstrength' is signed (false).
     */
    public static boolean isSigned_payload_signalstrength() {
        return false;
    }

    /**
     * Return whether the field 'payload.signalstrength' is an array (false).
     */
    public static boolean isArray_payload_signalstrength() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.signalstrength'
     */
    public static int offset_payload_signalstrength() {
        return (224 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.signalstrength'
     */
    public static int offsetBits_payload_signalstrength() {
        return 224;
    }

    /**
     * Return the value (as a short) of the field 'payload.signalstrength'
     */
    public short get_payload_signalstrength() {
        return (short)getUIntBEElement(offsetBits_payload_signalstrength(), 8);
    }

    /**
     * Set the value of the field 'payload.signalstrength'
     */
    public void set_payload_signalstrength(short value) {
        setUIntBEElement(offsetBits_payload_signalstrength(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.signalstrength'
     */
    public static int size_payload_signalstrength() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.signalstrength'
     */
    public static int sizeBits_payload_signalstrength() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: payload.lliandmqi
    //   Field type: short
    //   Offset (bits): 232
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'payload.lliandmqi' is signed (false).
     */
    public static boolean isSigned_payload_lliandmqi() {
        return false;
    }

    /**
     * Return whether the field 'payload.lliandmqi' is an array (false).
     */
    public static boolean isArray_payload_lliandmqi() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'payload.lliandmqi'
     */
    public static int offset_payload_lliandmqi() {
        return (232 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'payload.lliandmqi'
     */
    public static int offsetBits_payload_lliandmqi() {
        return 232;
    }

    /**
     * Return the value (as a short) of the field 'payload.lliandmqi'
     */
    public short get_payload_lliandmqi() {
        return (short)getUIntBEElement(offsetBits_payload_lliandmqi(), 8);
    }

    /**
     * Set the value of the field 'payload.lliandmqi'
     */
    public void set_payload_lliandmqi(short value) {
        setUIntBEElement(offsetBits_payload_lliandmqi(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'payload.lliandmqi'
     */
    public static int size_payload_lliandmqi() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'payload.lliandmqi'
     */
    public static int sizeBits_payload_lliandmqi() {
        return 8;
    }

}
