val updateState = taskKey[StateTransform]("update state")
val printEnvVars = taskKey[Unit]("Print envVars")
val doIt = taskKey[Unit]("do it")

updateState := {
  println(envVars.value)
  val extracted = Project extract state.value
  val newState = extracted.appendWithSession(Seq(envVars := Map("FOO" -> "bar")), state.value)
  new StateTransform(newState)
}

printEnvVars := {
  println(envVars.value)
}

doIt := printEnvVars.dependsOn(updateState).value
