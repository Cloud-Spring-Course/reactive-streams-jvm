package org.reactivestreams.spi;

/**
 * A Subscriber receives elements from a [[Publisher]] based on the [[Subscription]] it has.
 * The Publisher may supply elements as they become available, the Subscriber signals demand via 
 * [[Subscription#requestMore]] and elements from when supply and demand are both present.
 */
public interface Subscriber<T> {
  
  /**
   * The [[Publisher]] generates a [[Subscription]] upon [[Publisher#subscribe]] and passes
   * it on to the Subscriber named there using this method. The Publisher may choose to reject
   * the subscription request by calling [[#onError]] instead.
   * 
   * @param subscription The subscription which connects this subscriber to its publisher.
   */
  public void onSubscribe(Subscription subscription);
  
  /**
   * The [[Publisher]] calls this method to pass one element to this Subscriber. The element
   * must not be <code>null</code>. The Publisher must not call this method more often than
   * the Subscriber has signaled demand for via the corresponding [[Subscription]].
   * 
   * @param element The element that is passed from publisher to subscriber.
   */
  public void onNext(T element);
  
  /**
   * The [[Publisher]] calls this method in order to signal that it terminated normally.
   * No more elements will be forthcoming and none of the Subscriber’s methods will be
   * called hereafter.
   */
  public void onComplete();
  
  /**
   * The [[Publisher]] calls this method to signal that the stream of elements has failed
   * and is being aborted. The Subscriber should abort its processing as soon as possible.
   * No more elements will be forthcoming and none of the Subscriber’s methods will be
   * called hereafter.
   * 
   * This method is not intended to pass validation errors or similar from Publisher to Subscriber
   * in order to initiate an orderly shutdown of the exchange; it is intended only for fatal
   * failure conditions which make it impossible to continue processing further elements.
   * 
   * @param cause An exception which describes the reason for tearing down this stream.
   */
  public void onError(Throwable cause);
}
