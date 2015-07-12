import java.io.IOException;

/**
 * PacketJoinServer
 */
public class gt implements ff<fj> {
    private int entityId;
    private boolean hardcode;
    private adp.a gameMode;
    private int dimension;
    private oj difficulty;
    private int maxPlayers;
    private adr levelType;
    private boolean reducedDebugInfo;

    public gt() {
    }

    public gt(int entityId, adp.a gameMode, boolean var2, int dimension, oj difficulty, int maxPlayers, adr levelType, boolean reducedDebugInfo) {
        this.entityId = entityId;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.gameMode = gameMode;
        this.maxPlayers = maxPlayers;
        this.hardcode = var2;
        this.levelType = levelType;
        this.reducedDebugInfo = reducedDebugInfo;
    }

    /**
     * Read Packet
     *
     * @param packetBuffer PacketBuffer
     * @throws IOException
     */
    public void a(em packetBuffer) throws IOException {
        this.entityId = packetBuffer.readInt();
        short gameMode = packetBuffer.readUnsignedByte();
        this.hardcode = (gameMode & 8) == 8;
        int var2 = gameMode & -9;
        this.gameMode = adp.a.a(var2);
        this.dimension = packetBuffer.readByte();
        this.difficulty = oj.a(packetBuffer.readUnsignedByte());
        this.maxPlayers = packetBuffer.readUnsignedByte();
        this.levelType = adr.a(packetBuffer.c(16));
        if(this.levelType == null) {
            this.levelType = adr.b;
        }

        this.reducedDebugInfo = packetBuffer.readBoolean();
    }

    /**
     * Write Packet
     *
     * @param packetBuffer PacketBuffer
     * @throws IOException
     */
    public void b(em packetBuffer) throws IOException {
        packetBuffer.writeInt(this.entityId);
        int var1 = this.gameMode.a();
        if(this.hardcode) {
            var1 |= 8;
        }

        packetBuffer.writeByte(var1);
        packetBuffer.writeByte(this.dimension);
        packetBuffer.writeByte(this.difficulty.a());
        packetBuffer.writeByte(this.maxPlayers);
        packetBuffer.a(this.levelType.a());
        packetBuffer.writeBoolean(this.reducedDebugInfo);
    }

    /**
     * Handle Packet
     *
     * @param netHandler NetHandlerPlayClient
     */
    public void a(fj netHandler) {
        netHandler.a(this);

        // den wunderschönen Sound abspielen
        if (awz.reconnectTries > 0) {
            awz.reconnectTries = 0;
            for (int i = 0; i < 5; i++) {
                ave.A().W().a(bpf.a(new jy("minecraft", "note.pling"), 1));
            }
        }
    }

    public int a() {
        return this.entityId;
    }

    public boolean b() {
        return this.hardcode;
    }

    public adp.a c() {
        return this.gameMode;
    }

    public int d() {
        return this.dimension;
    }

    public oj e() {
        return this.difficulty;
    }

    public int f() {
        return this.maxPlayers;
    }

    public adr g() {
        return this.levelType;
    }

    public boolean h() {
        return this.reducedDebugInfo;
    }

}
