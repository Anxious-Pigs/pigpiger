package org.anxiouspigs.filepigger.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PigpigerSelector {

    private final Selector selector;

    /**
     * Create a new nioSelector.
     * @param selector
     */
    public PigpigerSelector(Selector selector) {
            this.selector = selector;
    }

    /**
     * Begin connecting to the given address.
     * @param address The address to connect to
     * @throws IOException If an I/O error occurs
     */
    public void connect(InetSocketAddress address) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Socket socket = socketChannel.socket();
        socket.setKeepAlive(true);
        socket.setTcpNoDelay(true);
        boolean connected = socketChannel.connect(address);
        if (!connected) {
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT);
            key.attach(socketChannel);
        }else {
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        }
    }

    /**
     * Create a server socket to listen for connections on.
     * @param address The address to bind
     * @throws IOException If an I/O error occurs
     */
    public void bind(InetSocketAddress address) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void pool() {
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
//        while (selectionKeyIterator.hasNext()) {
//            SelectionKey
//        }



    }

}
