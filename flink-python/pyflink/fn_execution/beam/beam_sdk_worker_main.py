################################################################################
#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
# limitations under the License.
################################################################################
import sys

# force to register the operations to SDK Harness
import pyflink.fn_execution.beam.beam_operations # noqa # pylint: disable=unused-import

# force to register the coders to SDK Harness
import pyflink.fn_execution.beam.beam_coders # noqa # pylint: disable=unused-import

import apache_beam.runners.worker.sdk_worker_main

# disable bundle processor shutdown
from apache_beam.runners.worker import sdk_worker
sdk_worker.DEFAULT_BUNDLE_PROCESSOR_CACHE_SHUTDOWN_THRESHOLD_S = 86400 * 30


def main():
    import logging
    # Remove all the built-in log handles
    logging.getLogger().handlers = []
    apache_beam.runners.worker.sdk_worker_main.main(sys.argv)
