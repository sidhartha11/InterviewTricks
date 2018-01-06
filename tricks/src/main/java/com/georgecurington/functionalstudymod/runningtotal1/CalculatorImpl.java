package com.georgecurington.functionalstudymod.runningtotal1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * This calculator will as an exercise perform various
 * types of operations on the input MiniInputIntf objects.
 * These are a few operations:
 * <ul>
 * <li> maintain a set of acronynms discovered for each PriceOject
 * <li> maintain a map of PriceObjects with the key being a unqiue ID
 * <li> maintain a count of how many times the PriceObject has been processed
 * <li> maintain a list of the highest share prices for a particular PriceObject
 * <li> maintain a global list of the n highest priced shares discovered so far
 * <li> maintain a n-second map of transactions
 * </ul>
 * </pre>
 * <pre>
 * @author george Curington
 * @since Dec 25 2017
 * @version 1.0
 * </pre>
 * 
 */
public class CalculatorImpl<T extends CombiningPriceOBject & MiniInputIntf > implements Calculator<T> {

	private static final boolean DEBUG=true;
	/** local internal ConcurrentHashMap **/
	ConcurrentMap<Long,PriceObject> map = new ConcurrentHashMap<>();
	
	/** local NSecondPriceObject Map **/
	ConcurrentMap<Long,NSecondPriceObjects> nsecond = new ConcurrentHashMap<>();

	private final CopyOnWriteArrayList<T> priceObjectList=new CopyOnWriteArrayList<>();

	private long threshHold=250l;  /** one second threshhold **/
	private AtomicLong oldTime = new AtomicLong(System.currentTimeMillis());
	
	/**
	 * 
	 */
	public CalculatorImpl() {
		super();
	}

	@Override
	public void process(T input) {
		/**
		 * check to see if the key is already mapped to a value. If it is not
		 * mapped, map the key,value pair, otherwise just return the value 
		 * currently mapped. 
		 */
		priceObjectList.add(input);
		if ( DEBUG ) {
		System.out.println("calculator processing >>>>>>" + input );
		}
		PriceObject po = new PriceObjectImpl(input);
		PriceObject val = map.putIfAbsent(input.getId(), po);
		if ( val != null ){
			/** this means the priceObject was already in the hash map **/
			if ( DEBUG ){
			System.out.println(">>>>>> PriceObject already present in hash map:"+val.getId());
			}
			updatePriceOject(val,input);
		} else {
			if ( DEBUG ) {
			System.out.println(">>>>>> placing new PriceObject in map:"+ input);
//			/** update the nsecond with a new item also **/
//			NSecondPriceObjects npo = new NSecondPriceObjectsImpl(po, po.getMilliseconds());
//			long id = po.getId();
//			npo = nsecond.putIfAbsent(id, npo);
//			if ( npo != null ){
//				System.out.println(">>>>>> this should not happen , npo should be null");
//			}
			}
		}
		

	}

	private void updatePriceOject(PriceObject val, T input) {
		/** update acronym list **/
		val.getAcronymns().add(input.getAcronymn());
		
		/** update counter **/
		val.updateCounter();
		
//		/** update the nsecond map **/
//		long oldtime = val.getMilliseconds();
//		long currenttime = input.getMilliseconds();
//		boolean toCreateOrNotToCreate =
//				currenttime - oldtime > threshHold;
//		if ( toCreateOrNotToCreate ) {
////			ConcurrentMap<IdTimeObject,NSecondPriceObjects> nsecond
//			
//			NSecondPriceObjects npo = new NSecondPriceObjectsImpl(val, currenttime);
//			long id = val.getId();
//			npo = nsecond.putIfAbsent(id, npo);
//			if ( npo != null ) {
//				if ( DEBUG ){
//				System.out.println(id + ">>>>>> duplicate updating list >>>>>>>" );
//				}
//				npo.getPriceObjectList().add(val);
//			}
//		};
	}
	
	private void updateNsecondMap(PriceObject val, T input) {
		
	
			
			NSecondPriceObjects npo = new NSecondPriceObjectsImpl(val, System.currentTimeMillis());
			long id = val.getId();
			npo = nsecond.putIfAbsent(id, npo);
			if ( npo != null ) {
				if ( DEBUG ){
				System.out.println(id + ">>>>>> duplicate updating list >>>>>>>" );
				}
				npo.getPriceObjectList().add(val);
			}
	}
	
	@Override
	public void dumpPriceObjects(){
		System.out.println("=============== dumping price objects(" + map.size() + ")=================" );
		map.forEach((k,v) -> { 
			System.out.println("key=" + k + ",val=" + v);
		});
		
		System.out.println("=============== dumping nsecond objects(" + nsecond.size() + ") =================" );
		
		nsecond.forEach((k,v) -> {
			System.out.println("key=" + k + ",val=" + v);
		});

	}

	/**
	 * @return the map
	 */
	@Override
	public final ConcurrentMap<Long, PriceObject> getMap() {
		return map;
	}

	/**
	 * @return the nsecond
	 */
	@Override
	public final ConcurrentMap<Long, NSecondPriceObjects> getNsecond() {
		return nsecond;
	}

	/**
	 * @return the priceObjectList
	 */
	@Override
	public final CopyOnWriteArrayList<T> getPriceObjectList() {
		return priceObjectList;
	}

}
