/**
 * GuiConnecting
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class awz extends axu {

    private static final AtomicInteger a = new AtomicInteger(0);
    private static final Logger f = LogManager.getLogger();
    private ek g;
    private boolean h;
    private final axu i;

    /**
     * Die Disconnect Reason
     */
    private String disconnectReason;
    /**
     * Eine Liste, die die Disconnect Reason enthält, aber auf die Breite der GUI gesplittet wurde.
     */
    private List<String> disconnectReasonSplit;

    public static int reconnectTries = 0;

    public awz(axu var, ave var1, bde var2) {
        reconnectTries = 0;

        this.j = var1;
        this.i = var;
        bdd var3 = bdd.a(var2.b);
        var1.a((bdb) null);
        var1.a(var2);
        this.a(var3.a(), var3.b());
    }

    public awz(axu var, ave var1, String var2, int var3) {
        reconnectTries = 0;

        this.j = var1;
        this.i = var;
        var1.a((bdb) null);
        this.a(var2, var3);
    }

    /**
     * Verbindet sich mit einem Server, wobei die letzte Disconnect Reason angezeigt wird.
     *
     * @param parentScreen     Die letzte GUI.
     * @param minecraft        Die Minecraft Instanz.
     * @param serverData       Die IP und der Port des Servers.
     * @param disconnectReason Die vorherige Disconnect-Reason.
     */
    public awz(axu parentScreen, ave minecraft, bde serverData, String disconnectReason) {
        this.disconnectReason = disconnectReason;
        reconnectTries++;

        this.j = minecraft;
        this.i = parentScreen;
        bdd var3 = bdd.a(serverData.b);
        minecraft.a((bdb) null);
        minecraft.a(serverData);
        this.a(var3.a(), var3.b());
    }

    /**
     * Connectet zu einem bestimmten Server.
     *
     * @param ip   Die IP des Servers.
     * @param port Der Port des Servers.
     */
    private void a(final String ip, final int port) {
        f.info("Connecting to " + ip + ", " + port);
        (new Thread("Server Connector #" + a.incrementAndGet()) {
            public void run() {
                InetAddress varx = null;

                try {
                    if (awz.this.h) {
                        return;
                    }

                    varx = InetAddress.getByName(ip);
                    awz.this.g = ek.a(varx, port, awz.this.j.t.f());
                    awz.this.g.a(new bcx(awz.this.g, awz.this.j, awz.this.i));
                    awz.this.g.a(new jc(47, ip, port, el.d));
                    awz.this.g.a(new jl(awz.this.j.L().e()));
                } catch (UnknownHostException var5) {
                    if (awz.this.h) {
                        return;
                    }

                    awz.f.error("Couldn\'t connect to server", var5);
                    awz.this.j.a(new axh(awz.this.i, "connect.failed", new fb("disconnect.genericReason", "Unknown host")));
                } catch (Exception var6) {
                    if (awz.this.h) {
                        return;
                    }

                    awz.f.error("Couldn\'t connect to server", var6);
                    String var1x = var6.toString();
                    if (varx != null) {
                        String var2 = varx.toString() + ":" + port;
                        var1x = var1x.replaceAll(var2, "");
                    }

                    awz.this.j.a(new axh(awz.this.i, "connect.failed", new fb("disconnect.genericReason", var1x)));
                }

            }
        }).start();
    }

    public void e() {
        if (this.g != null) {
            if (this.g.g()) {
                this.g.a();
            } else {
                this.g.l();
            }
        }

    }

    /**
     * Key Typed
     * Überschrieben, damit nicht ESC gedrückt werden kann
     *
     * @param ch   Der char, der gedrückt wurde.
     * @param code Der keycode des chars, der gedrückt wurde.
     */
    protected void a(char ch, int code) {
    }

    /**
     * Init GUI
     */
    public void b() {
        // Button-list clearen
        this.n.clear();
        // cancel Button hinzufügen
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 120 + 12, bnq.a("gui.cancel")));

        // Disconnect Reason auf Breite splitten
        if (disconnectReason != null)
            this.disconnectReasonSplit = this.q.c(disconnectReason, this.l - 50);
    }

    /**
     * Action Performed
     *
     * @param button Der Button, der gedrückt wurde.
     */
    protected void a(avs button) {
        // cancel-Button
        if (button.k == 0) {
            this.h = true;
            if (this.g != null) {
                this.g.a(new fa("Aborted"));
            }

            this.j.a(this.i);
        }

    }

    /**
     * Draw Screen
     *
     * @param mouseX       Mouse x-Koordinate
     * @param mouseY       Mouse y-Koordinate
     * @param partialTicks partialTicks
     */
    public void a(int mouseX, int mouseY, float partialTicks) {
        // Draw menu background
        this.c();
        // Connecting bzw. Loggin in
        if (this.g == null) {
            this.a(this.q, bnq.a("connect.connecting") + (reconnectTries > 0 ? " (" + reconnectTries + " tries)" : ""), this.l / 2, this.m / 2 - 50, 16777215);
        } else {
            this.a(this.q, bnq.a("connect.authorizing") + (reconnectTries > 0 ? " (" + reconnectTries + " tries)" : ""), this.l / 2, this.m / 2 - 50, 16777215);
        }

        // vorherige Disconnect Reason
        if (this.disconnectReasonSplit != null) {
            int var3 = this.m / 2 - 30;
            for (Iterator var4 = this.disconnectReasonSplit.iterator(); var4.hasNext(); var3 += this.q.a) {
                String var5 = (String) var4.next();
                this.a(this.q, var5, this.l / 2, var3, 16777215);
            }
        }

        super.a(mouseX, mouseY, partialTicks);
    }
}
