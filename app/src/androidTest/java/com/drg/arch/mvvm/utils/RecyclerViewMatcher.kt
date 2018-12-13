package com.drg.arch.mvvm.utils

import android.content.res.Resources
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Created by viktor.chukholskiy
 * 9/21/18.
 */
class RecyclerViewMatcher(private val recyclerViewId: Int) {

	fun atPosition(position: Int): Matcher<View> {
		return atPositionOnView(position, -1)
	}

	private fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
		return object : TypeSafeMatcher<View>() {
			var resources: Resources? = null
			var childView: View? = null

			override fun describeTo(description: Description) {
				var idDescription = Integer.toString(recyclerViewId)
				if (this.resources != null) {
					idDescription = try {
						this.resources!!.getResourceName(recyclerViewId)
					} catch (var4: Resources.NotFoundException) {
						"$recyclerViewId (resource name not found)"
					}
				}

				description.appendText("RecyclerView with id: $idDescription at position: $position")
			}

			public override fun matchesSafely(view: View): Boolean {

				this.resources = view.resources

				if (childView == null) {
					val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
					if (recyclerView?.id == recyclerViewId) {
						val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
						childView = viewHolder?.itemView
					} else {
						return false
					}
				}

				return if (targetViewId == -1) {
					view === childView
				} else {
					val targetView = childView?.findViewById<View>(targetViewId)
					view === targetView
				}
			}
		}
	}

	companion object {
		fun withItemCount(count: Int): Matcher<View> {
			return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
				override fun describeTo(description: Description?) {
					description?.appendText("RecyclerView with item count: $count")
				}

				override fun matchesSafely(item: RecyclerView?): Boolean {
					return item?.adapter?.itemCount == count
				}
			}
		}
	}
}