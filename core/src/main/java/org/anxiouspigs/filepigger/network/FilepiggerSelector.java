package org.anxiouspigs.filepigger.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class FilepiggerSelector {

    private final Selector selector;

    /**
     * Create a new nioSelector.
     * @param selector
     */
    public FilepiggerSelector(Selector selector) {
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
        socketChannel.socket().setKeepAlive(true);
        socketChannel.socket().setTcpNoDelay(true);
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

    /**
     * Polling and handling IO events.
     * @throws IOException If an I/O error occurs
     */
    public void pool() throws IOException {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
        while (selectionKeyIterator.hasNext()) {
            SelectionKey selectionKey = selectionKeyIterator.next();
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.socket().setKeepAlive(true);
                socketChannel.socket().setTcpNoDelay(true);
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.println("accept");
            }

            if(selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                int bytesReadSize = socketChannel.read(byteBuffer);
                System.out.println(bytesReadSize);
                System.out.println(new String(byteBuffer.array(), 0, bytesReadSize));
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                System.out.println("read");
            }

            if (selectionKey.isWritable()) {
                SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                byteBuffer.put("Hello Filepigger !".getBytes(Charset.forName("utf-8")));
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_WRITE);
                System.out.println("write");
            }

            if (selectionKey.isConnectable()) {
                SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                socketChannel.finishConnect();
                selectionKey.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                System.out.println("connect");
            }

            selectionKeyIterator.remove();
        }
    }

}
